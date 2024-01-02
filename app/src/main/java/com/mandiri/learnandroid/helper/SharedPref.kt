package com.mandiri.learnandroid.helper

import android.content.Context
import android.content.SharedPreferences

class SharedPref(context: Context) {

    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences(SHARED_PREF, Context.MODE_PRIVATE)

    fun saveToken(token: String) {
        sharedPreferences.edit().putString(TOKEN_KEY, token).apply()
    }

    fun getToken(): String {
        return sharedPreferences.getString(TOKEN_KEY, null) ?: ""
    }

    fun clearAll() {
        sharedPreferences.edit().clear().apply()
    }

    companion object {
        private const val SHARED_PREF = "mypref"
        private const val TOKEN_KEY = "token"
    }
}