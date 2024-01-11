package com.mandiri.learnandroid.data.remote

import com.mandiri.learnandroid.data.ServiceMandiri
import com.mandiri.learnandroid.data.model.TransactionResponse
import retrofit2.Response

class TransactionRemoteDataSourceImpl constructor(private val service: ServiceMandiri) :
    TransactionRemoteDataSource {

    override suspend fun getTransaction(): Response<List<TransactionResponse>> {
        return service.getTransaction()
    }

}