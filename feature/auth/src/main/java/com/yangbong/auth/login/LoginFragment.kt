package com.yangbong.auth.login

import android.os.Bundle
import android.view.View
import android.view.animation.AnimationUtils
import androidx.fragment.app.activityViewModels
import com.yangbong.auth.social_login_manager.KakaoLoginManager
import com.yangbong.core_ui.base.BindingFragment
import com.yangbong.core_ui.extension.setOnSingleClickListener
import com.yangbong.core_ui.util.EventObserver
import com.yangbong.damedame.auth.R
import com.yangbong.damedame.auth.databinding.FragmentLoginBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class LoginFragment : BindingFragment<FragmentLoginBinding>(R.layout.fragment_login) {

    @Inject
    lateinit var kakaoLoginManager: KakaoLoginManager

    private val loginViewModel by activityViewModels<LoginViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loginViewModel.getFcmToken()
        initClickEvent()
        initLoginObserver()
        initLoginFailureMessageObserver()
        initMoveToHomeObserver()
        initMoveToSetProfileObserver()
        setLogoScaleAnimation()
    }

    private fun initClickEvent() {
        with(binding) {
            kakaoLoginLayout.setOnSingleClickListener {
                loginViewModel.updatePlatform(KAKAO)
                kakaoLoginManager.startKakaoLogin {
                    loginViewModel.updateSocialToken(it)
                }
            }
            // TODO("확장 가능성을 고려함. 추가 소셜 플랫폼 로그인 구현시 이곳에 구현")
        }
    }

    private fun initLoginObserver() {
        loginViewModel.socialToken.observe(viewLifecycleOwner) {
            loginViewModel.postLogin()
        }
    }

    private fun initLoginFailureMessageObserver() {
        /**
         * @author onseok
         * 서버가 구현안되었기 때문에, 로그인 실패해도 임시로 프로필 설정 화면으로 넘어가도록 구현하였습니다.
         */
//        loginViewModel.loginFailureMessage.observe(viewLifecycleOwner) {
//            Toast.makeText(requireContext(), "로그인에 실패 하였습니다", Toast.LENGTH_SHORT).show()
//        }
        loginViewModel.loginFailureMessage.observe(viewLifecycleOwner) {
            navigateSetProfileActivity()
        }
    }

    private fun initMoveToSetProfileObserver() {
        loginViewModel.navigateToSetProfile.observe(
            viewLifecycleOwner,
            EventObserver {
                navigateSetProfileActivity()
            }
        )
    }

    private fun initMoveToHomeObserver() {
        loginViewModel.navigateToHome.observe(
            viewLifecycleOwner,
            EventObserver {
                navigateMainActivity()
            }
        )
    }

    private fun setLogoScaleAnimation() {
        AnimationUtils.loadAnimation(requireActivity(), R.anim.logo_scale).apply {
            binding.ivLogo.startAnimation(this)
        }
    }

    private fun navigateMainActivity() {
        mainNavigator.navigateMain(requireActivity())
        requireActivity().finish()
    }

    private fun navigateSetProfileActivity() {
        mainNavigator.navigateSetProfile(
            context = requireContext()
        )
        /**
         * @author onseok
         * 뒤로가기를 허용하기 위해 아래의 코드를 주석 처리 하였습니다.
         */
        //  requireActivity().finish()
    }

    companion object {
        private const val KAKAO = "KAKAO"
    }
}