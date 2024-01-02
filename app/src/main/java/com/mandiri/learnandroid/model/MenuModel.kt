package com.mandiri.learnandroid.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class MenuModel(
    var name: String,
    var image: Int,
) : Parcelable;