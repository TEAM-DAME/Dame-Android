package com.yangbong.set_profile.ui

import android.Manifest
import android.content.Context
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.text.InputFilter
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.core.net.toUri
import androidx.core.widget.addTextChangedListener
import com.yangbong.core_ui.base.BindingActivity
import com.yangbong.core_ui.constant.SetProfileNicknameConstant.*
import com.yangbong.core_ui.extension.setOnSingleClickListener
import com.yangbong.core_ui.extension.setQueryDebounce
import com.yangbong.core_ui.extension.shortToast
import com.yangbong.core_ui.util.EventObserver
import com.yangbong.damedame.set_profile.R
import com.yangbong.damedame.set_profile.databinding.ActivitySetProfileBinding
import com.yangbong.set_profile.view.ImageBottomSheetDialog
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.rxjava3.disposables.Disposable
import timber.log.Timber
import java.io.File
import java.io.FileOutputStream
import java.util.*
import java.util.regex.Pattern

@AndroidEntryPoint
class SetProfileActivity :
    BindingActivity<ActivitySetProfileBinding>(R.layout.activity_set_profile) {
    private val setProfileViewModel by viewModels<SetProfileViewModel>()
    private lateinit var platform: String
    private lateinit var socialToken: String
    private lateinit var fcmToken: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.setProfileViewModel = setProfileViewModel
        setProfileViewModel.getProfileImage()
        initExtraData()
        initEditTextFilter()
        initDuplicateProfileId()
        initProfileImageClickListener()
        initNextButtonClickListener()
        initNavigateToSetCharacterObserver()
        initProfileIdLengthMessage()
        initPermissionErrorMessage()
    }

    private fun initExtraData() {
        platform = intent.getStringExtra("platform") ?: ""
        socialToken = intent.getStringExtra("socialToken") ?: ""
        fcmToken = intent.getStringExtra("fcmToken") ?: ""
    }

    private fun initEditTextFilter() {
        binding.etSetProfileId.filters = arrayOf(
            InputFilter { source, _, _, _, _, _ ->
                val noSpecialCharacterRegex = "^[0-9a-zA-Zㄱ-ㅎㅏ-ㅣ가-힣ㆍᆢ]*$"
                val noSpecialCharacterPattern = Pattern.compile(noSpecialCharacterRegex)
                val isPossibleChar =
                    source.isNullOrEmpty() || noSpecialCharacterPattern.matcher(source).matches()
                if (!isPossibleChar) setProfileViewModel.updateProfileIdState(NO_SPECIAL_CHARACTER)
                return@InputFilter if (isPossibleChar) source else ""
            },
            InputFilter.LengthFilter(11)
        )
    }

    private fun initDuplicateProfileId() {
        val profileIdEditTextSubscription: Disposable =
            binding.etSetProfileId.setQueryDebounce(object :
                    (String) -> Unit {
                override fun invoke(it: String) {
                    if (it.isNotEmpty()) setProfileViewModel.checkDuplicateNickName()
                }
            })
        setProfileViewModel.addDisposable(profileIdEditTextSubscription)
    }

    private fun initProfileIdLengthMessage() {
        binding.etSetProfileId.addTextChangedListener {
            val message = when (binding.etSetProfileId.text?.length) {
                in 0..10 -> HAS_NO_STATE
                11 -> OVER_TEXT_LIMIT
                else -> throw IllegalStateException("Maximum nickname length is 10")
            }
            setProfileViewModel.updateProfileIdState(message)
        }
    }

    private fun initProfileImageClickListener() {
        binding.activitySetProfileImage.setOnSingleClickListener {
            ImageBottomSheetDialog(
                onGalleryClick = ::getImageFromGallery,
                onCameraClick = ::getImageFromCamera
            ).show(
                supportFragmentManager,
                this.javaClass.name
            )
        }
    }

    private fun initNextButtonClickListener() {
        binding.btnNext.setOnSingleClickListener {
            setProfileViewModel.postSignUp(platform, socialToken, fcmToken)
        }
    }

    private fun navigateSetCharacterActivity() {
        mainNavigator.navigateSetCharacter(this)
        finish()
    }

    private fun initNavigateToSetCharacterObserver() {
        setProfileViewModel.navigateToSetCharacter.observe(
            this,
            EventObserver {
                navigateSetCharacterActivity()
            }
        )
    }

    private fun initPermissionErrorMessage() {
        setProfileViewModel.errorMessage.observe(
            this,
            EventObserver {
                shortToast(it)
            }
        )
    }

    private fun getImageFromGallery() {
        galleryResult.launch("image/*")
    }

    private val galleryResult = registerForActivityResult(
        ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        uri?.let {
            setProfileViewModel.uploadAndDownloadFile(
                File(
                    createCopyAndReturnRealPath(
                        this,
                        it
                    ).toString()
                )
            )
        }
    }

    private fun getImageFromCamera() {
        cameraPermissionLauncher.launch(Manifest.permission.CAMERA)
    }

    private val getCameraResult = registerForActivityResult(
        ActivityResultContracts.TakePicturePreview()
    ) {
        setProfileViewModel.uploadAndDownloadFile(
            bitmapToFile(this, it)
        )
    }

    private val cameraPermissionLauncher =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
            if (isGranted) { /* 카메라 권한 허용 */
                getCameraResult.launch(null)
            } else { /* 카메라 권한 허용 안함 */
                shortToast("카메라 권한을 허용해야 합니다!")
            }
        }
}