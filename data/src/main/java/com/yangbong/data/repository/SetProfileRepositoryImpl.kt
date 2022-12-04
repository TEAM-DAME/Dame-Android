package com.yangbong.data.repository

import com.amplifyframework.core.Amplify
import com.yangbong.core_data.exception.RetrofitFailureStateException
import com.yangbong.data.local.data_source.LocalPreferenceUserDataSource
import com.yangbong.data.remote.call_adapter.NetworkState
import com.yangbong.data.remote.data_source.RemoteSetProfileDataSource
import com.yangbong.data.remote.model.request.SignUpRequest
import com.yangbong.domain.entity.request.DomainSignUpRequest
import com.yangbong.domain.entity.response.DomainSignUpResponse
import com.yangbong.domain.repository.SetProfileRepository
import timber.log.Timber
import java.io.File
import javax.inject.Inject

class SetProfileRepositoryImpl @Inject constructor(
    private val setProfileDataSource: RemoteSetProfileDataSource,
    private val localPreferenceUserDataSource: LocalPreferenceUserDataSource
) : SetProfileRepository {
    override suspend fun checkDuplicateProfileNickname(profileNickname: String): Result<Boolean> {

        when (val response = setProfileDataSource.checkDuplicateProfileNickname(profileNickname)) {
            is NetworkState.Success -> return Result.success(
                response.body.data.available
            )
            is NetworkState.Failure -> return Result.failure(
                RetrofitFailureStateException(
                    response.error,
                    response.code
                )
            )
            is NetworkState.NetworkError -> Timber.tag("${this.javaClass.name}_checkDuplicateNickname")
                .d(response.error)
            is NetworkState.UnknownError -> Timber.tag("${this.javaClass.name}_checkDuplicateNickname")
                .d(response.t)
        }
        return Result.failure(IllegalStateException("NetworkError or UnKnownError please check timber"))
    }

    override suspend fun postSignUp(signUpRequest: DomainSignUpRequest): Result<DomainSignUpResponse> {
        val response = setProfileDataSource.postSignUp(
            SignUpRequest(
                platform = signUpRequest.platform,
                socialToken = signUpRequest.socialToken,
                fcmToken = signUpRequest.fcmToken,
                nickName = signUpRequest.nickname,
                profileImageUrl = signUpRequest.profileImageUrl
            )
        )

        when (response) {
            is NetworkState.Success -> return Result.success(
                DomainSignUpResponse(
                    userId = response.body.data?.userId ?: -1,
                    jwtToken = response.body.data?.jwtToken ?: ""
                )
            )
            is NetworkState.Failure -> return Result.failure(
                RetrofitFailureStateException(
                    response.error,
                    response.code
                )
            )
            is NetworkState.NetworkError -> Timber.tag("${this.javaClass.name}_postKakaoLogin")
                .d(response.error)
            is NetworkState.UnknownError -> Timber.tag("${this.javaClass.name}_postKakaoLogin")
                .d(response.t)
        }
        return Result.failure(IllegalStateException("NetworkError or UnKnownError please check timber"))
    }

    override fun saveUserId(userId: Int) {
        localPreferenceUserDataSource.saveUserId(userId)
    }

    override fun saveUserProfileNickname(profileNickname: String) {
        localPreferenceUserDataSource.saveUserProfileNickname(profileNickname)
    }

    override fun saveUserProfileImageUrl(profileImageUrl: String) {
        localPreferenceUserDataSource.saveUserProfileImageUrl(profileImageUrl)
    }

    override fun saveAccessToken(accessToken: String) {
        localPreferenceUserDataSource.saveAccessToken(accessToken)
    }

    override fun getUserProfileImageUrl(): String {
        return localPreferenceUserDataSource.getUserProfileImageUrl()
    }


    /**
     * AWS S3 버킷에 파일을 업로드하고
     * 성공한다면 업로드된 파일의 URL 을 콜백으로 받아오기 위한 메소드
     * 실패한다면 `StorageException` 예외를 던진다.
     *
     * @author onseok
     * @param file = 업로드하고자 하는 파일
     * @param onResult = 업로드 후 callback function
     */
    override suspend fun uploadAndDownloadFile(file: File, onResult: (String) -> Unit) {
        Amplify.Storage.uploadFile(file.name, file,
            { storageUploadFileResult ->
                Timber.tag("${this.javaClass.name}_Amplify").d("Successfully uploaded: ${storageUploadFileResult.key}")
                Amplify.Storage.getUrl(
                    file.name,
                    {
                        Timber.tag("${this.javaClass.name}_Amplify").d("Successfully generated: ${it.url}")
                        onResult(it.url.toString())
                    },
                    {
                        Timber.tag("${this.javaClass.name}_Amplify").d("URL generation failure $it")
                        onResult("")
                    }
                )
            },
            {
                Timber.tag("${this.javaClass.name}_Amplify").d("Upload failed : $it")
                onResult("")
            }
        )
    }
}