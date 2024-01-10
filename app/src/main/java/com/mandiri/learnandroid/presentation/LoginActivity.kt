package com.mandiri.learnandroid.presentation

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.mandiri.learnandroid.databinding.ActivityLoginBinding
import com.mandiri.learnandroid.helper.SharedPreferenceHelper
import com.mandiri.learnandroid.utils.Navigation
import dagger.hilt.android.AndroidEntryPoint
import java.util.UUID
import javax.inject.Inject

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    @Inject
    lateinit var preferences: SharedPreferenceHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)

        if (doesTokenExist()) {
            Toast.makeText(applicationContext, "You're already login!", Toast.LENGTH_SHORT).show()
            Navigation.getInstace().replace(this, HomeMainActivity::class.java)
        }
        setContentView(binding.root)
        renderLogin()
    }

    private fun renderLogin() {
        val password = "password"
        binding.apply {
            btnLogin.setOnClickListener {
                tvWrongPassword.visibility = View.INVISIBLE

                if (etPassword.text.isEmpty()) {
                    Toast.makeText(
                        applicationContext,
                        "Password cannot be empty",
                        Toast.LENGTH_SHORT
                    ).show()
                } else if (etPassword.text.toString() != password) {
                    Toast.makeText(
                        applicationContext,
                        "Wrong password sorry :(",
                        Toast.LENGTH_SHORT
                    ).show()
                    tvWrongPassword.visibility = View.VISIBLE
                } else {
                    val token = UUID.randomUUID().toString()
                    preferences.saveToken(token)

                    Toast.makeText(applicationContext, "Welcome admin!", Toast.LENGTH_SHORT).show()
                    Navigation.getInstace()
                        .replace(this@LoginActivity, HomeMainActivity::class.java)
                }
            }

            btnRegister.setOnClickListener {
                Navigation.getInstace().replace(this@LoginActivity, HomeMainActivity::class.java)
            }
        }
    }

    private fun doesTokenExist(): Boolean {
        return preferences.getToken() != ""
    }

}
