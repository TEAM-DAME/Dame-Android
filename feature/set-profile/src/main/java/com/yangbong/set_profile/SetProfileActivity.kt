package com.yangbong.set_profile

import android.os.Bundle
import android.text.InputFilter
import androidx.activity.viewModels
import androidx.core.widget.addTextChangedListener
import com.yangbong.core_ui.base.BindingActivity
import com.yangbong.core_ui.constant.SetProfileNicknameConstant.*
import com.yangbong.core_ui.extension.setOnSingleClickListener
import com.yangbong.core_ui.extension.setQueryDebounce
import com.yangbong.core_ui.util.EventObserver
import com.yangbong.damedame.set_profile.R
import com.yangbong.damedame.set_profile.databinding.ActivitySetProfileBinding
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.rxjava3.disposables.Disposable
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
        initNextButtonClickListener()
        initNavigateToSetCharacterObserver()
        initProfileIdLengthMessage()
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

}