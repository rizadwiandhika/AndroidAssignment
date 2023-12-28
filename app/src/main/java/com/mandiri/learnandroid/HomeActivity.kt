package com.mandiri.learnandroid

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import com.mandiri.learnandroid.RegisterActivity.Companion.KEY_ADDRESS
import com.mandiri.learnandroid.RegisterActivity.Companion.KEY_AGE
import com.mandiri.learnandroid.RegisterActivity.Companion.KEY_GENDER
import com.mandiri.learnandroid.RegisterActivity.Companion.KEY_NAME
import com.mandiri.learnandroid.databinding.ActivityHomeBinding
import com.mandiri.learnandroid.databinding.ActivityRegisterBinding

class HomeActivity: AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)



    }

}