package com.mandiri.learnandroid.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.util.Date

@Parcelize
data class NotificationModel(
	val date: Date,
	val title: String,
	val content: String,
) : Parcelable
