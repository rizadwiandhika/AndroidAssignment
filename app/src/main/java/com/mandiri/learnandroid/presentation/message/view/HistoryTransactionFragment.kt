package com.mandiri.learnandroid.presentation.message.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.mandiri.learnandroid.adapter.HistoryTransactionAdapter
import com.mandiri.learnandroid.databinding.FragmentHistoryTransactionBinding
import com.mandiri.learnandroid.model.HistoryTransactionModel
import com.mandiri.learnandroid.model.StatusTransaction

class HistoryTransactionFragment(private val fragmentReplacer: (Fragment) -> Unit) : Fragment() {

    private var _binding: FragmentHistoryTransactionBinding? = null

    private val binding get() = _binding!!

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHistoryTransactionBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        render()
    }

    private fun render() {
        val adapter = HistoryTransactionAdapter(generateData())
        adapter.setOnClickHandler { transaction ->
            fragmentReplacer.invoke(DetailTransactionFragment(transaction.title))
        }

        binding.rvHistoryTransaction.adapter = adapter
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
