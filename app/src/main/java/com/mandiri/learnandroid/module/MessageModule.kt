package com.mandiri.learnandroid.module

import com.mandiri.learnandroid.data.ServiceMandiri
import com.mandiri.learnandroid.data.remote.TransactionRemoteDataSource
import com.mandiri.learnandroid.data.remote.TransactionRemoteDataSourceImpl
import com.mandiri.learnandroid.data.repository.TransactionRepository
import com.mandiri.learnandroid.data.repository.TransactionRepositoryImpl
import com.mandiri.learnandroid.usecase.TransactionUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MessageModule {

    @Provides
    @Singleton
    fun provideTransactionRemoteDataSource(service: ServiceMandiri): TransactionRemoteDataSource =
        TransactionRemoteDataSourceImpl(service)

//    @Provides
//    @Singleton
//    fun provideTransactionRepository(remoteDataSource: TransactionRemoteDataSource): TransactionRepository =
//        TransactionRepositoryImpl(remoteDataSource)

    @Provides
    @Singleton
    fun provideTransactionRepository(transactionRepositoryImpl: TransactionRepositoryImpl): TransactionRepository =
        transactionRepositoryImpl

    @Provides
    @Singleton
    fun provideTransactionUseCase(repo: TransactionRepositoryImpl): TransactionUseCase =
        TransactionUseCase(repo)
}