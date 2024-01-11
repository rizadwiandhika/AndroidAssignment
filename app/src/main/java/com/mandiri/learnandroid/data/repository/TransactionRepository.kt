package com.mandiri.learnandroid.data.repository

import com.mandiri.learnandroid.data.model.TransactionResponse
import retrofit2.Response

interface TransactionRepository {
    suspend fun getTransaction(): Response<List<TransactionResponse>>
}