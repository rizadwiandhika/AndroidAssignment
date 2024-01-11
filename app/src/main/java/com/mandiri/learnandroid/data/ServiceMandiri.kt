package com.mandiri.learnandroid.data

import com.mandiri.learnandroid.data.model.TransactionResponse
import retrofit2.Response
import retrofit2.http.GET

// This interface is for Retrofit! not related to application architecture
interface ServiceMandiri {

    @GET("transaction")
    suspend fun getTransaction(): Response<List<TransactionResponse>>

}