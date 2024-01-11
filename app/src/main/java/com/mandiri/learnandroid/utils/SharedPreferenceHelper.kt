package com.mandiri.learnandroid.utils

import android.content.SharedPreferences

class SharedPreferenceHelper(private val sharedPref: SharedPreferences) {

    fun saveToken(token: String) {
        sharedPref.edit().putString(TOKEN_KEY, token).apply()
    }

    fun getToken(): String {
        return sharedPref.getString(TOKEN_KEY, null) ?: ""
    }

    fun clearAll() {
        sharedPref.edit().clear().apply()
    }

    companion object {
        private const val TOKEN_KEY = "token"
    }
}