package com.mandiri.learnandroid.module

import android.content.Context
import android.content.SharedPreferences
import com.mandiri.learnandroid.helper.SharedPreferenceHelper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object SharedPreferenceModule {

    private const val PREF_NAME = "LEARN_ANDROID_PREFERENCE"

    @Provides
    fun provideSharedPreference(@ApplicationContext context: Context): SharedPreferences {
        return context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
    }

    @Provides
    fun provideSharedPreferenceHelper(sharedPreferences: SharedPreferences): SharedPreferenceHelper {
        return SharedPreferenceHelper(sharedPreferences)
    }

}