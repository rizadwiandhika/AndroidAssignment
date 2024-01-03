package com.mandiri.learnandroid.presentation

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.mandiri.learnandroid.databinding.ActivityRegisterBinding

class RegisterActivity: AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding
    private val riza = "Riza Dwi"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnRegister.setOnClickListener {
            handleRegister()
        }

    }

    private fun handleRegister() {
        // Apply is like attribute setter
        binding.etInputAddress.apply {
            // textColors = "Red"
            // text = "Some Text"
        }

        // inside with block, "this" will refer to the "binding" variable, not the class object
        with(binding) {
            // to access class attribute:
            // this@RegisterActivity.riza

            val name = etInputName.text.toString()
            val age = etInputAge.text.toString()
            val address = etInputAddress.text.toString()
            val gender = etInputGender.text.toString()

            if (name.isEmpty() || age.isEmpty() || address.isEmpty() || gender.isEmpty()) {
                Toast.makeText(applicationContext, "All Fields must be filled!", Toast.LENGTH_SHORT).show()
            } else {
                val extra = mapOf(KEY_NAME to name, KEY_AGE to age, KEY_ADDRESS to address, KEY_GENDER to gender)
                navigateTo(ProfileActivity::class.java, extra)
            }
        }
    }

    private fun navigateTo(clazz: Class<*>, extra: Map<String, String>?) {
        val intent = Intent(this, clazz)
        extra?.forEach { (key, value) -> intent.putExtra(key, value) }
        startActivity(intent)
        finish()
    }

    companion object {
        const val KEY_NAME = "KEY_NAME"
        const val KEY_GENDER = "KEY_GENDER"
        const val KEY_ADDRESS = "KEY_ADDRESS"
        const val KEY_AGE = "KEY_AGE"
    }

}
