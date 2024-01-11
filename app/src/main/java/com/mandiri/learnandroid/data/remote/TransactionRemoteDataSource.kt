package com.mandiri.learnandroid.data.remote

import com.mandiri.learnandroid.data.model.TransactionResponse
import retrofit2.Response

interface TransactionRemoteDataSource {

    suspend fun getTransaction(): Response<List<TransactionResponse>>

}