package com.yangbong.set_profile

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.yangbong.core_ui.base.BindingFragment
import com.yangbong.core_ui.constant.SetProfileIdConstant
import com.yangbong.core_ui.extension.setOnSingleClickListener
import com.yangbong.damedame.set_profile.databinding.FragmentSetProfileBinding
import com.yangbong.damedame.set_profile.R

class SetProfileFragment: BindingFragment<FragmentSetProfileBinding>(R.layout.fragment_set_profile) {
    private val setProfileViewModel by viewModels<SetProfileViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initProfileIdLengthMessage()
        initNextButtonClickListener()
    }

    private fun initNextButtonClickListener() {
        binding.etSetProfileId.addTextChangedListener {
            val message = when (binding.etSetProfileId.text.length) {
                in 0..9 -> SetProfileIdConstant.HAS_NO_STATE
                10 -> SetProfileIdConstant.OVER_TEXT_LIMIT
                else -> throw IllegalStateException("Maximum nickname length is 7")
            }
            setProfileViewModel.updateProfileIdState(message)
        }
    }

    private fun initProfileIdLengthMessage() {
        binding.btnNext.setOnSingleClickListener {
//            setProfileViewModel.postSetProfile()
            val selectCharacterFragment = SelectCharacterFragment()
            replaceFragment(selectCharacterFragment)
        }
    }

    private fun replaceFragment(fragment: Fragment){
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.setProfileFrameLayout,fragment).commit()
    }
}