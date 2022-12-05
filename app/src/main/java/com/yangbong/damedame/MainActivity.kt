package com.yangbong.damedame

import android.content.Intent
import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.yangbong.core_ui.base.BindingActivity
import com.yangbong.core_ui.extension.setOnSingleClickListener
import com.yangbong.damedame.databinding.ActivityMainBinding
import com.yangbong.damedame.factory.DameDameFragmentFactory
import com.yangbong.write_diary.WriteDiaryActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BindingActivity<ActivityMainBinding>(R.layout.activity_main) {

    override fun onCreate(savedInstanceState: Bundle?) {
        supportFragmentManager.fragmentFactory = DameDameFragmentFactory(this)
        super.onCreate(savedInstanceState)
        initTransactionToHome()
        initFabWriteClickListener()
        syncBottomNavWithNavController()
    }

    private fun initFabWriteClickListener() {
        binding.fabWrite.setOnSingleClickListener {
            val intent = Intent(this, WriteDiaryActivity::class.java)
            startActivity(intent)
        }
    }

    private fun syncBottomNavWithNavController() {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController
        findViewById<BottomNavigationView>(R.id.bottom_nav)
            .setupWithNavController(navController)
    }

    private fun initTransactionToHome() {
        transactionToHome = {
            findViewById<BottomNavigationView>(R.id.bottom_nav)
                .selectedItemId = R.menu.menu_main
        }
    }

    companion object {
        lateinit var transactionToHome: () -> Unit
    }


}