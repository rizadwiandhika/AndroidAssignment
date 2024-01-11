package com.mandiri.learnandroid.presentation

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.mandiri.learnandroid.constant.enums.LoginStatus.ALREADY_LOGGED_IN
import com.mandiri.learnandroid.constant.enums.LoginStatus.EMPTY
import com.mandiri.learnandroid.constant.enums.LoginStatus.INVALID_CREDENTIAL
import com.mandiri.learnandroid.constant.enums.LoginStatus.SUCCESS
import com.mandiri.learnandroid.databinding.ActivityLoginBinding
import com.mandiri.learnandroid.presentation.viewmodel.LoginViewModel
import com.mandiri.learnandroid.utils.Navigation
import com.mandiri.learnandroid.utils.ToastUtil
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {

    private val loginViewModel: LoginViewModel by viewModels<LoginViewModel>()

    private lateinit var binding: ActivityLoginBinding

    @Inject
    lateinit var navigation: Navigation

    @Inject
    lateinit var toast: ToastUtil

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        loginViewModel.checkIfHasBeenAuthenticated()
        
        setupView()
        observeViewModel()
    }

    private fun setupView() {
        binding.btnLogin.setOnClickListener {
            loginViewModel.authenticate(binding.etPassword.text.toString())
        }

        binding.btnRegister.setOnClickListener {
            navigation.replace(this@LoginActivity, HomeMainActivity::class.java)
        }
    }

    private fun observeViewModel() {
        loginViewModel.authenticationLiveData.observe(this) {
            binding.tvWrongPassword.visibility = View.INVISIBLE
            if (it == null) return@observe

            when (it) {
                SUCCESS -> navigation.replace(this, HomeMainActivity::class.java)
                ALREADY_LOGGED_IN -> {
                    toast.show("Already Logged In")
                    navigation.replace(this, HomeMainActivity::class.java)
                }

                EMPTY -> {
                    toast.show("Password cannot be empty")
                }

                INVALID_CREDENTIAL -> {
                    binding.tvWrongPassword.visibility = View.VISIBLE
                    toast.show("Wrong password sorry :(")
                }
            }
        }
    }


}
