package com.mandiri.learnandroid.data.repository

import com.mandiri.learnandroid.data.model.TransactionResponse
import com.mandiri.learnandroid.data.remote.TransactionRemoteDataSource
import retrofit2.Response

class TransactionRepositoryImpl constructor(private val transactionRemoteDataSource: TransactionRemoteDataSource) :
    TransactionRepository {

    override suspend fun getTransaction(): Response<List<TransactionResponse>> {
        return transactionRemoteDataSource.getTransaction()
    }

}