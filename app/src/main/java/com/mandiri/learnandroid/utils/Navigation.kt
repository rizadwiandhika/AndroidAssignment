package com.mandiri.learnandroid.utils

import android.content.Context
import android.content.Intent
import android.os.Parcelable
import androidx.appcompat.app.AppCompatActivity

class Navigation {

    companion object {

        private var instance: Navigation? = null

        fun getInstance(): Navigation {
            return instance ?: synchronized(this) {
                instance ?: Navigation()
            }
        }
    }

    fun goto(from: Context, to: Class<out AppCompatActivity>, extra: Map<String, Parcelable>) {
        from.startActivity(Intent(from, to).also {
            extra.forEach { (key, parcelable) ->
                it.putExtra(
                    key,
                    parcelable
                )
            }
        })
    }


    fun goto(from: AppCompatActivity, to: Class<out AppCompatActivity>) {
        from.startActivity(Intent(from, to))
    }

    fun replace(from: AppCompatActivity, to: Class<out AppCompatActivity>) {
        goto(from, to)
        from.finish()
    }

    fun replace(
        from: AppCompatActivity,
        to: Class<out AppCompatActivity>,
        extra: Map<String, Parcelable>
    ) {
        goto(from, to, extra)
        from.finish()
    }

}