package com.mandiri.learnandroid.utils

import android.content.Context
import android.widget.Toast

class ToastUtil(private val context: Context) {
    fun show(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }
}