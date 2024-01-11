package com.mandiri.learnandroid.usecase

import com.mandiri.learnandroid.data.model.TransactionResponse
import com.mandiri.learnandroid.data.repository.TransactionRepository
import retrofit2.Response
import javax.inject.Inject

class TransactionUseCase @Inject constructor(private val transactionRepository: TransactionRepository) {

    suspend fun getTransaction(): Response<List<TransactionResponse>> {
        return transactionRepository.getTransaction()
    }

}