package com.mandiri.learnandroid.model

data class HistoryTransactionModel(
    val date: String,
    val title: String,
    val amount: String,
    val description: String,
    val isSuccess: Int
)


enum class StatusTransaction(val value: Int) {
    SUCCESS(1),
    PENDING(2),
    FAILED(3)
}