package com.mandiri.learnandroid.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mandiri.learnandroid.model.HistoryTransactionModel
import com.mandiri.learnandroid.model.StatusTransaction
import com.mandiri.learnandroid.usecase.TransactionUseCase
import com.mandiri.learnandroid.utils.UIState
import com.mandiri.learnandroid.utils.extend.postError
import com.mandiri.learnandroid.utils.extend.postLoading
import com.mandiri.learnandroid.utils.extend.postSuccess
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HistoryTransactionViewModel @Inject constructor(private val transactionUseCase: TransactionUseCase) :
    ViewModel() {

    private var _allHistoryTransactions: List<HistoryTransactionModel> = listOf()
    private val _historyTransactionLiveData =
        MutableLiveData<UIState<List<HistoryTransactionModel>>>()


    val historyTransactionLiveData: LiveData<UIState<List<HistoryTransactionModel>>> get() = _historyTransactionLiveData

    fun fetchHistoryTransactions() = viewModelScope.launch {
        _historyTransactionLiveData.postLoading()

        val response = transactionUseCase.getTransaction()
        if (response.isSuccessful) {
            _allHistoryTransactions = response.body()?.map {
                HistoryTransactionModel(
                    "2022-10-12",
                    it.name,
                    it.balance ?: "",
                    it.transferMethod ?: "",
                    it.flagDebitCredit
                )
            } ?: listOf()
            _historyTransactionLiveData.postSuccess(_allHistoryTransactions)
        } else {
            _historyTransactionLiveData.postError(Error(response.errorBody().toString()))
        }
    }


    fun filterByTransactionStatus(status: String) {
        var data = _allHistoryTransactions

        data = if (status == "All Transaction") data else data.filter {
            when (it.status) {
                StatusTransaction.SUCCESS.value -> return@filter status == "Success"
                StatusTransaction.PENDING.value -> return@filter status == "Pending"
                StatusTransaction.FAILED.value -> return@filter status == "Failed"
            }
            return@filter false
        }

        _historyTransactionLiveData.postSuccess(data)
    }


}