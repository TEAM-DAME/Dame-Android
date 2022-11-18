package com.yangbong.set_profile

import android.os.Bundle
import android.text.InputFilter
import androidx.activity.viewModels
import androidx.core.widget.addTextChangedListener
import com.yangbong.core_ui.base.BindingActivity
import com.yangbong.core_ui.constant.SetProfileIdConstant.*
import com.yangbong.core_ui.extension.setOnSingleClickListener
import com.yangbong.core_ui.extension.setQueryDebounce
import com.yangbong.damedame.set_profile.R
import com.yangbong.damedame.set_profile.databinding.ActivitySetProfileBinding
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.rxjava3.disposables.Disposable
import java.util.regex.Pattern

@AndroidEntryPoint
class SetProfileActivity : BindingActivity<ActivitySetProfileBinding>(R.layout.activity_set_profile) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initLayout()
    }

    private fun initLayout() {
        val setProfileFragment = SetProfileFragment()
        supportFragmentManager.beginTransaction()
            .replace(R.id.setProfileFrameLayout,setProfileFragment).commit()
    }
}