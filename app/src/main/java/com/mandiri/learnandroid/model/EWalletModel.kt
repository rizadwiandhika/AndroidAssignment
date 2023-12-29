package com.mandiri.learnandroid.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class EWalletModel(
    var name: String,
    var image: Int,
    var balance: Double,
    var isConnected: Boolean
) : Parcelable