package com.mandiri.learnandroid.utils

import com.mandiri.learnandroid.R
import com.mandiri.learnandroid.model.EWalletModel
import com.mandiri.learnandroid.model.HistoryTransactionModel
import com.mandiri.learnandroid.model.MenuModel
import com.mandiri.learnandroid.model.NotificationModel
import com.mandiri.learnandroid.model.SavingDepositModel
import com.mandiri.learnandroid.model.StatusTransaction
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


        fun createEWalletDummyList(): MutableList<EWalletModel> {
            return mutableListOf(
                EWalletModel(
                    name = "Gopay",
                    image = R.drawable.money_bag,
                    balance = 100000.0,
                    isConnected = true
                ),
                EWalletModel(
                    name = "LinkAja",
                    image = R.drawable.money_bag,
                    balance = 200000.0,
                    isConnected = true
                ),
                EWalletModel(
                    name = "Dana",
                    image = R.drawable.baseline_visibility_24,
                    balance = 0.0,
                    isConnected = false
                ),
                EWalletModel(
                    name = "SPay",
                    image = R.drawable.baseline_email_24,
                    balance = 0.0,
                    isConnected = false
                ),
            )
        }

        fun createSavingDepositDummyList(): MutableList<SavingDepositModel> {
            return mutableListOf(
                SavingDepositModel(
                    savingName = "Tabungan Now",
                    accountNumber = "111111111",
                    image = R.drawable.saving_card,
                    balance = 100000
                ),
                SavingDepositModel(
                    savingName = "Tabungan Now",
                    accountNumber = "222222222",
                    image = R.drawable.saving_card,
                    balance = 200000
                ),
                SavingDepositModel(
                    savingName = "Tabungan Now",
                    accountNumber = "333333333",
                    image = R.drawable.saving_card,
                    balance = 300000
                ),
                SavingDepositModel(
                    savingName = "Tabungan Now",
                    accountNumber = "444444444",
                    image = R.drawable.saving_card,
                    balance = 400000
                ),
                SavingDepositModel(
                    savingName = "Tabungan Now",
                    accountNumber = "555555555",
                    image = R.drawable.saving_card,
                    balance = 500000
                ),
            )
        }

        fun createMenuDummyList(): MutableList<MenuModel> {
            return mutableListOf(
                MenuModel("Transfer", R.drawable.ic_transfer),
                MenuModel("Top Up", R.drawable.ic_transfer),
                MenuModel("Payment", R.drawable.ic_transfer),
                MenuModel("Investment", R.drawable.ic_transfer),
                MenuModel("Accounts", R.drawable.ic_transfer),
                MenuModel("Withdrawal", R.drawable.ic_transfer),
                MenuModel("Credit", R.drawable.ic_transfer),
                MenuModel("Donate", R.drawable.ic_transfer),
            );
        }

        fun createHistoryTransactions(): List<HistoryTransactionModel> {
            return listOf(
                HistoryTransactionModel(
                    "03 Jan 2024",
                    "QR Payment",
                    "Rp 100.000",
                    "Pembayaran QR ke TOIS VAPOR DEPOK 311259196512",
                    StatusTransaction.PENDING.value
                ),
                HistoryTransactionModel(
                    "03 Jan 2024",
                    "Transfer",
                    "Rp 100.000",
                    "Pembayaran QR ke TOIS VAPOR DEPOK 311259196512",
                    StatusTransaction.SUCCESS.value
                ),
                HistoryTransactionModel(
                    "03 Jan 2024",
                    "Transfer",
                    "Rp 100.000",
                    "Pembayaran QR ke TOIS VAPOR DEPOK 311259196512",
                    StatusTransaction.FAILED.value
                ),
                HistoryTransactionModel(
                    "03 Jan 2024",
                    "QR Payment",
                    "Rp 100.000",
                    "Pembayaran QR ke TOIS VAPOR DEPOK 311259196512",
                    StatusTransaction.SUCCESS.value
                ),
                HistoryTransactionModel(
                    "03 Jan 2024",
                    "QR Payment",
                    "Rp 100.000",
                    "Pembayaran QR ke TOIS VAPOR DEPOK 311259196512",
                    StatusTransaction.SUCCESS.value
                ),
                HistoryTransactionModel(
                    "03 Jan 2024",
                    "Transfer",
                    "Rp 100.000",
                    "Pembayaran QR ke TOIS VAPOR DEPOK 311259196512",
                    StatusTransaction.SUCCESS.value
                ),
                HistoryTransactionModel(
                    "03 Jan 2024",
                    "QR Payment",
                    "Rp 100.000",
                    "Pembayaran QR ke TOIS VAPOR DEPOK 311259196512",
                    StatusTransaction.PENDING.value
                ),
                HistoryTransactionModel(
                    "03 Jan 2024",
                    "QR Payment",
                    "Rp 100.000",
                    "Pembayaran QR ke TOIS VAPOR DEPOK 311259196512",
                    StatusTransaction.FAILED.value
                ),
            )
        }
    }
}