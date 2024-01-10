package com.mandiri.learnandroid.module

import com.mandiri.learnandroid.utils.ConfirmationDialogUtil
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object UtilModule {

    @Provides
    fun provideConfirmationDialogUtil(): ConfirmationDialogUtil {
        return ConfirmationDialogUtil()
    }
    
}