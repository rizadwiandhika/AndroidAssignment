package com.mandiri.learnandroid

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.mandiri.learnandroid.RegisterActivity.Companion.KEY_ADDRESS
import com.mandiri.learnandroid.RegisterActivity.Companion.KEY_AGE
import com.mandiri.learnandroid.RegisterActivity.Companion.KEY_GENDER
import com.mandiri.learnandroid.RegisterActivity.Companion.KEY_NAME
import com.mandiri.learnandroid.databinding.ActivityProfileBinding

class ProfileActivity: AppCompatActivity() {

    private lateinit var binding: ActivityProfileBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            tvHeaderName.text = intent.getStringExtra(KEY_NAME)
            tvName.text = intent.getStringExtra(KEY_NAME)
            tvBirthday.text = intent.getStringExtra(KEY_AGE)
            tvPhone.text = intent.getStringExtra(KEY_ADDRESS)
            tvIg.text = intent.getStringExtra(KEY_GENDER)
        }
    }
}