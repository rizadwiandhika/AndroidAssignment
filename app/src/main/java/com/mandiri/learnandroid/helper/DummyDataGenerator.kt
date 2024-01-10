package com.mandiri.learnandroid.helper

import com.mandiri.learnandroid.model.NotificationModel
import java.util.Date

class DummyDataGenerator {
    companion object {
        fun getNotificationData(): List<NotificationModel> {
            return listOf(
                NotificationModel(
                    Date(2018, 1, 12),
                    "Special offer for you!",
                    "Here goes some very long content of the notification, Here goes some very long content of the notification......"
                ),
                NotificationModel(
                    Date(2019, 2, 12),
                    "Special offer for you!",
                    "\"Here goes some very long content of the notification, Here goes some very long content of the notification......"
                ),
                NotificationModel(
                    Date(2020, 3, 12),
                    "Special offer for you!",
                    "\"Here goes some very long content of the notification, Here goes some very long content of the notification......"
                ),
                NotificationModel(
                    Date(2021, 4, 12),
                    "Special offer for you!",
                    "\"Here goes some very long content of the notification, Here goes some very long content of the notification......"
                ),
                NotificationModel(
                    Date(2022, 5, 12),
                    "Special offer for you!",
                    "\"Here goes some very long content of the notification, Here goes some very long content of the notification......"
                ),
            )
        }
    }
}