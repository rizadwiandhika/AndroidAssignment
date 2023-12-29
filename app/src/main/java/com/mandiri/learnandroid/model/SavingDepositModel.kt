package com.mandiri.learnandroid.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class SavingDepositModel (
    val savingName: String,
    val accountNumber: String,
    val image: Int,
    val balance: Int
): Parcelable