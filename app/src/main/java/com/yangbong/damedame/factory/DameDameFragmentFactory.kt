package com.yangbong.damedame.factory

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory

class DameDameFragmentFactory(activity: AppCompatActivity) : FragmentFactory() {
    override fun instantiate(classLoader: ClassLoader, className: String): Fragment {
        return super.instantiate(classLoader, className)
    }
}