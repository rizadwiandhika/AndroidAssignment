package com.mandiri.learnandroid.module

import android.content.Context
import com.mandiri.learnandroid.utils.ConfirmationDialogUtil
import com.mandiri.learnandroid.utils.Navigation
import com.mandiri.learnandroid.utils.ToastUtil
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object UtilModule {

    @Provides
    fun provideConfirmationDialogUtil(): ConfirmationDialogUtil {
        return ConfirmationDialogUtil()
    }

    @Provides
    fun provideNavigation(): Navigation {
        return Navigation()
    }

    @Provides
    fun provideToastUtil(@ApplicationContext context: Context): ToastUtil {
        return ToastUtil(context)
    }

}