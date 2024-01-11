package com.mandiri.learnandroid.data.model

import com.google.gson.annotations.SerializedName

data class TransactionResponse(
    @SerializedName("id")
    val id: String?,

    @SerializedName("image_url")
    val imageUrl: String,

    @SerializedName("metode_trf")
    val transferMethod: String?,

    @SerializedName("name")
    val name: String,

    @SerializedName("nominal_saldo")
    val balance: String?,

    @SerializedName("flag_debit_credit")
    val flagDebitCredit: Int,
)