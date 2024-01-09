package com.mandiri.learnandroid.presentation.message.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import com.google.android.material.R.layout.support_simple_spinner_dropdown_item
import com.mandiri.learnandroid.adapter.HistoryTransactionAdapter
import com.mandiri.learnandroid.base.BaseFragment
import com.mandiri.learnandroid.databinding.FragmentHistoryTransactionBinding
import com.mandiri.learnandroid.model.HistoryTransactionModel
import com.mandiri.learnandroid.model.StatusTransaction
import com.mandiri.learnandroid.utils.ConfirmationDialogUtil
import com.mandiri.learnandroid.utils.Navigation

class HistoryTransactionFragment(private val fragmentReplacer: (Fragment) -> Unit) :
    BaseFragment<FragmentHistoryTransactionBinding>() {

    private lateinit var dialog: ConfirmationDialogUtil

    override fun inflate(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): FragmentHistoryTransactionBinding {
        return FragmentHistoryTransactionBinding.inflate(inflater, container, false)
    }

    override fun setupView(view: View, savedInstanceState: Bundle?) {
        render()
    }

    private fun render() {
        val filter = arrayOf("All Transaction", "Success", "Pending", "Failed")
        val historyTransactionAdapter = HistoryTransactionAdapter(generateData()) { transaction ->
            // When using Fragment
            // fragmentReplacer.invoke(DetailTransactionFragment(transaction.title))

            // When using Activity
            val status = enumValues<StatusTransaction>().find { it.value == transaction.status }
            dialog.show(
                requireContext(),
                transaction.title,
                "$status • ${transaction.description}",
                "See Detail"
            ) {
                Navigation.getInstace().goto(
                    requireActivity(),
                    DetailTransactionActivity::class.java,
                    mapOf(DetailTransactionActivity.DATA_TRANSACTION to transaction)
                )
            }
        }

        binding.apply {
            rvHistoryTransaction.adapter = historyTransactionAdapter
            spFilter.adapter = ArrayAdapter(
                requireContext(),
                support_simple_spinner_dropdown_item,
                filter
            )
            spFilter.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    adapterView: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    p3: Long
                ) {
                    val spinnerValue = adapterView?.getItemAtPosition(position).toString()
                    binding.tvFilterStatus.text = spinnerValue
                    historyTransactionAdapter.setData(filterByTransactionStatus(spinnerValue))
                }

                override fun onNothingSelected(p0: AdapterView<*>?) {
                    TODO("Not yet implemented")
                }

            }
        }
    }

    private fun filterByTransactionStatus(status: String): List<HistoryTransactionModel> {
        val data = generateData()
        return if (status == "All Transaction") data else data.filter {
            when (it.status) {
                StatusTransaction.SUCCESS.value -> return@filter status == "Success"
                StatusTransaction.PENDING.value -> return@filter status == "Pending"
                StatusTransaction.FAILED.value -> return@filter status == "Failed"
            }
            return@filter false
        }
    }

    private fun generateData(): List<HistoryTransactionModel> {
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
