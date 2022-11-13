package com.yangbong.domain.use_case.login

data class LoginUseCases(
    val getAccessToken: GetAccessToken,
    val saveAccessToken: SaveAccessToken,
    val getIsFirstVisited: GetIsFirstVisited,
    val setIsFirstVisited: SetIsFirstVisited,
    val saveUserNickname: SaveUserNickname,
    val saveUserProfileImageUrl: SaveUserProfileImageUrl,
    val postLogin: PostLogin
)