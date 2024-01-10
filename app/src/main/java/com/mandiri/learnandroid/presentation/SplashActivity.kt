package com.mandiri.learnandroid.presentation

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.mandiri.learnandroid.utils.Navigation
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Handler(Looper.getMainLooper()).postDelayed({
            Navigation.getInstace().replace(this@SplashActivity, LoginActivity::class.java)
        }, 1500)
    }

}