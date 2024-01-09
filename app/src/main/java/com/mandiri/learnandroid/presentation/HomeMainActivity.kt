package com.mandiri.learnandroid.presentation

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.mandiri.learnandroid.R
import com.mandiri.learnandroid.databinding.HomeMainActivityBinding
import com.mandiri.learnandroid.helper.SharedPreferenceHelper
import com.mandiri.learnandroid.presentation.home.HomeFragment
import com.mandiri.learnandroid.presentation.message.MessageFragment
import com.mandiri.learnandroid.utils.ConfirmationDialogUtil
import javax.inject.Inject

class HomeMainActivity @Inject constructor(private val sharedPreferenceHelper: SharedPreferenceHelper) :
    AppCompatActivity() {

    private lateinit var binding: HomeMainActivityBinding
    private lateinit var confirmDialog: ConfirmationDialogUtil

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        confirmDialog = ConfirmationDialogUtil.getInstance()
        binding = HomeMainActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.bottomNavigation.setOnNavigationItemSelectedListener(
            onNavigationItemSelectedListener
        )
        if (savedInstanceState == null) {
            binding.bottomNavigation.selectedItemId = R.id.navigationHome
        }
    }

    override fun onStart() {
        super.onStart()
        Log.d("MY_DEBUG", "on start is called")
    }

    private val onNavigationItemSelectedListener =
        BottomNavigationView.OnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigationHome -> {
                    replaceFragment(HomeFragment(sharedPreferenceHelper))
                    return@OnNavigationItemSelectedListener true
                }

                R.id.navigationSetting -> {
                    replaceFragment(SettingFragment())
                    return@OnNavigationItemSelectedListener true
                }

                R.id.navigationPromotion -> {
                    replaceFragment(PromotionFragment())
                    return@OnNavigationItemSelectedListener true
                }

                R.id.navigationMessage -> {
                    replaceFragment(MessageFragment() { f ->
                        replaceFragment(f)
                    })
                    return@OnNavigationItemSelectedListener true
                }

                R.id.navigationLogout -> {
                    showLogoutDialog()
                    return@OnNavigationItemSelectedListener false
                }
            }
            false
        }

    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, fragment)
//			.add(R.id.fragmentContainer, fragment, fragment.javaClass.simpleName)
//			.addToBackStack(fragment.javaClass.simpleName)
            .commit()
    }

    private fun showLogoutDialog() {
//        val builder = AlertDialog.Builder(this)
//        builder.setTitle("Logout")
//            .setMessage("Yakin ingin keluar dari Livin' ?")
//            .setPositiveButton("Ya") { p1, num -> handleLogout() }
//            .setNegativeButton("Tidak") { dialog, num -> }
//            .show()

        confirmDialog.show(
            this,
            "Leaving Livin'?",
            "Make sure that you have completed all your activities! Thank you for using Livin'",
            "Logout",
            this::handleLogout
        )
    }

    private fun handleLogout() {
        sharedPreferenceHelper.clearAll()
        startActivity(Intent(this, LoginActivity::class.java))
        finish()
    }
}
