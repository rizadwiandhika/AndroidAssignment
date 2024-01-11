package com.mandiri.learnandroid.presentation.message.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.google.android.material.R.layout.support_simple_spinner_dropdown_item
import com.mandiri.learnandroid.adapter.HistoryTransactionAdapter
import com.mandiri.learnandroid.base.BaseFragment
import com.mandiri.learnandroid.constant.enums.UIStateStatus
import com.mandiri.learnandroid.databinding.FragmentHistoryTransactionBinding
import com.mandiri.learnandroid.model.HistoryTransactionModel
import com.mandiri.learnandroid.model.StatusTransaction
import com.mandiri.learnandroid.presentation.viewmodel.HistoryTransactionViewModel
import com.mandiri.learnandroid.utils.ConfirmationDialogUtil
import com.mandiri.learnandroid.utils.Navigation
import com.mandiri.learnandroid.utils.ToastUtil
import com.mandiri.learnandroid.utils.UIState
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class HistoryTransactionFragment(private val fragmentReplacer: (Fragment) -> Unit) :
    BaseFragment<FragmentHistoryTransactionBinding>() {

    companion object {
        val filter = arrayOf("All Transaction", "Success", "Pending", "Failed")
    }

    private val viewModel: HistoryTransactionViewModel by viewModels<HistoryTransactionViewModel>()

    private lateinit var historyTransactionAdapter: HistoryTransactionAdapter

    @Inject
    lateinit var dialog: ConfirmationDialogUtil

    @Inject
    lateinit var navigation: Navigation

    @Inject
    lateinit var toast: ToastUtil

    override fun inflate(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): FragmentHistoryTransactionBinding {
        return FragmentHistoryTransactionBinding.inflate(inflater, container, false)
    }

    override fun setupView(view: View, savedInstanceState: Bundle?) {
        render()
        fetchData()
        observeViewModel()
    }

    private fun render() {
        historyTransactionAdapter = HistoryTransactionAdapter(::handleHistoryTransactionClicked)
        val filterAdapter =
            ArrayAdapter(requireContext(), support_simple_spinner_dropdown_item, filter)

        binding.rvHistoryTransaction.adapter = historyTransactionAdapter
        binding.spFilter.adapter = filterAdapter

        binding.spFilter.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                adapterView: AdapterView<*>?,
                view: View?,
                position: Int,
                p3: Long
            ) {
                val spinnerValue = adapterView?.getItemAtPosition(position).toString()

                binding.tvFilterStatus.text = spinnerValue
                viewModel.filterByTransactionStatus(spinnerValue)
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
            }

        }
    }

    private fun fetchData() {
        viewModel.fetchHistoryTransactions()
    }

    private fun observeViewModel() {
        viewModel.historyTransactionLiveData.observe(
            viewLifecycleOwner,
            ::displayHistoryTransactions
        )
    }

    private fun displayHistoryTransactions(uiState: UIState<List<HistoryTransactionModel>>) {
        when (uiState.status) {
            UIStateStatus.LOADING -> {}
            UIStateStatus.SUCCESS -> {
                historyTransactionAdapter.setData(uiState.data ?: mutableListOf())
            }

            UIStateStatus.ERROR -> {
                toast.show("Fetch error for history transactions")
            }
        }
    }

    private fun handleHistoryTransactionClicked(transaction: HistoryTransactionModel) {
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
            navigation.goto(
                requireActivity(),
                DetailTransactionActivity::class.java,
                mapOf(DetailTransactionActivity.DATA_TRANSACTION to transaction)
            )
        }
    }

}
