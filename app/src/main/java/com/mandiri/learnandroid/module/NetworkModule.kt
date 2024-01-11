package com.mandiri.learnandroid.module

import com.mandiri.learnandroid.data.ServiceMandiri
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideOkHttpClient() = OkHttpClient.Builder().build()

    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl("https://private-54eacf-fazztrack.apiary-mock.com/")
        .client(okHttpClient)
        .build()

    @Provides
    @Singleton
    fun provideServiceMandiri(retrofit: Retrofit): ServiceMandiri =
        retrofit.create(ServiceMandiri::class.java)
}