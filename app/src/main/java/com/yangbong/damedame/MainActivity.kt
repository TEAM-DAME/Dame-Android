package com.yangbong.damedame

import android.content.Intent
import android.os.Bundle
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.yangbong.core_ui.base.BindingActivity
import com.yangbong.core_ui.extension.setOnSingleClickListener
import com.yangbong.damedame.adapter.MainViewPagerAdapter
import com.yangbong.damedame.databinding.ActivityMainBinding
import com.yangbong.damedame.factory.DameDameFragmentFactory
import com.yangbong.main.home.HomeFragment
import com.yangbong.main.profile.ProfileFragment
import com.yangbong.main.search.SearchFragment
import com.yangbong.write_diary.WriteDiaryActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BindingActivity<ActivityMainBinding>(R.layout.activity_main) {

    private lateinit var mainViewPagerAdapter: MainViewPagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        supportFragmentManager.fragmentFactory = DameDameFragmentFactory(this)
        super.onCreate(savedInstanceState)
        initAdapter()
        syncBottomNavWithVp()
        initFabWriteClickListener()
        initTransactionToHome()
    }

    private fun initAdapter() {
        binding.vpMain.adapter = MainViewPagerAdapter(this).also { mainViewPagerAdapter = it }
        mainViewPagerAdapter.fragmentList = listOf(
            supportFragmentManager.fragmentFactory.instantiate(
                classLoader,
                HomeFragment::class.java.name
            ),
            supportFragmentManager.fragmentFactory.instantiate(
                classLoader,
                SearchFragment::class.java.name
            ),
            supportFragmentManager.fragmentFactory.instantiate(
                classLoader,
                ProfileFragment::class.java.name
            )
        )
    }

    private fun syncBottomNavWithVp() {
        binding.vpMain.isUserInputEnabled = false

        binding.bottomNav.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.menu_home -> binding.vpMain.setCurrentItem(0, false)
                R.id.menu_search -> binding.vpMain.setCurrentItem(1, false)
                R.id.menu_my_profile -> binding.vpMain.setCurrentItem(2, false)
            }
            return@setOnItemSelectedListener true
        }
    }

    private fun initFabWriteClickListener() {
        binding.fabWrite.setOnSingleClickListener {
            val intent = Intent(this, WriteDiaryActivity::class.java)
            startActivity(intent)
        }
    }

    private fun initTransactionToHome() {
        transactionToHome = {
            binding.vpMain.setCurrentItem(0, false)
            binding.bottomNav.selectedItemId = R.id.menu_home
        }
    }

    companion object {
        lateinit var transactionToHome: () -> Unit
    }
}