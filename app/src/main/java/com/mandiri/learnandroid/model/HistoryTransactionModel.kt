package com.mandiri.learnandroid.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class HistoryTransactionModel(
    val date: String,
    val title: String,
    val amount: String,
    val description: String,
    val status: Int
) : Parcelable


enum class StatusTransaction(val value: Int) {
    SUCCESS(1),
    PENDING(2),
    FAILED(3)
}