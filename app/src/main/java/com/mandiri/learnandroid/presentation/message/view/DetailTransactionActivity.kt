package com.mandiri.learnandroid.presentation.message.view

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.mandiri.learnandroid.databinding.FragmentDetailHistoryTransactionBinding
import com.mandiri.learnandroid.model.HistoryTransactionModel
import com.mandiri.learnandroid.model.StatusTransaction

class DetailTransactionActivity : AppCompatActivity() {
    private lateinit var binding: FragmentDetailHistoryTransactionBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = FragmentDetailHistoryTransactionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        render()
    }

    private fun render() {
        val transaction = intent.getParcelableExtra(DATA_TRANSACTION) as HistoryTransactionModel?

        with(binding) {
            tvTitle.text = transaction?.title ?: "No title"
            tvStatus.text = translateStatus(transaction?.status)
            componentToolbar.ivArrowBack.setOnClickListener {
                onBackPressed()
            }
        }

    }

    private fun translateStatus(status: Int?): String {
        when (status) {
            StatusTransaction.SUCCESS.value -> {
                return "Success"
            }

            StatusTransaction.PENDING.value -> {
                return "Pending"
            }

            StatusTransaction.FAILED.value -> {
                return "Failed"
            }
        }
        return "Unknown"
    }

    companion object {
        private const val DATA_TRANSACTION = "dataTransaction"


        fun navigateToDetailTransaction(activity: Activity, data: HistoryTransactionModel) =
            Intent(activity.applicationContext, DetailTransactionActivity::class.java).apply {
                putExtra(DATA_TRANSACTION, data)
                activity.startActivity(this)
            }
    }
}