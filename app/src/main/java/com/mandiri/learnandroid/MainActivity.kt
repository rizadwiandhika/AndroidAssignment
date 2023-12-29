package com.mandiri.learnandroid

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.mandiri.learnandroid.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

        private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        handleLogin()
    }

    private fun handleLogin() {
//        val loginButton = findViewById<Button>(R.id.btnLogin)
//        val registerButton = findViewById<Button>(R.id.btnRegister)
//        val wrongPassword = findViewById<TextView>(R.id.tvWrongPassword)
//        val passwordInp = findViewById<EditText>(R.id.etPassword)

        val password = "password"
        binding.apply {
          btnLogin.setOnClickListener {
              tvWrongPassword.visibility = View.INVISIBLE

              if (etPassword.text.isEmpty()) {
                  Toast.makeText(applicationContext, "Password cannot be empty", Toast.LENGTH_SHORT).show()
              } else if (etPassword.text.toString() != password) {
                  Toast.makeText(applicationContext, "Wrong password sorry :(", Toast.LENGTH_SHORT).show()
                  tvWrongPassword.visibility =  View.VISIBLE
              } else {
                  Toast.makeText(applicationContext, "Welcome admin!", Toast.LENGTH_SHORT).show()
                  navigateTo(HomeActivity::class.java)
              }
          }

            btnRegister.setOnClickListener {
                navigateTo(RegisterActivity::class.java)
            }
        }
    }

    private fun navigateTo(c: Class<*>) {
        val intent = Intent(this, c)
        startActivity(intent)
        finish()

        callback(10, op2 = {num -> num + 20}, op1 = {num -> num + 20})
    }

    private fun callback(num: Int, op1: (Int) -> Int, op2: (Int) -> Int): Int {
        return op2.invoke(op1.invoke(1));
    }



    private fun simpleCalculator() {
//        val etInput1 = findViewById<EditText>(R.id.etInputNumber1)
//        val etInput2 = findViewById<EditText>(R.id.etInputNumber2)
//
//        val tvResult = findViewById<TextView>(R.id.tvResult)
//        val button = findViewById<Button>(R.id.btnSubmit)
//
//        button.setOnClickListener {
//            if (etInput1.text.isEmpty() || etInput2.text.isEmpty()) {
//                Toast.makeText(applicationContext, "Input should be provided", Toast.LENGTH_SHORT).show()
//            } else {
//                val result = etInput1.text.toString().toInt() * etInput2.text.toString().toInt()
//                Toast.makeText(applicationContext, "Result = ${result.toString()}", Toast.LENGTH_SHORT).show()
//                tvResult.text = result.toString()
//            }
//        }
    }

}