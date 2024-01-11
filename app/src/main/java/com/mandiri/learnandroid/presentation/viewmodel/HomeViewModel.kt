package com.mandiri.learnandroid.presentation.viewmodel

import android.os.Handler
import android.os.Looper
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mandiri.learnandroid.R
import com.mandiri.learnandroid.utils.DummyDataGenerator
import com.mandiri.learnandroid.model.EWalletModel
import com.mandiri.learnandroid.model.MenuModel
import com.mandiri.learnandroid.model.SavingDepositModel
import com.mandiri.learnandroid.utils.UIState
import com.mandiri.learnandroid.utils.extend.postError
import com.mandiri.learnandroid.utils.extend.postLoading
import com.mandiri.learnandroid.utils.extend.postSuccess
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor() : ViewModel() {

    private var _isShowAllSavingLiveData = MutableLiveData(false)
    private var _isShowBalanceLiveData = MutableLiveData(false)
    private val _eWalletLiveData = MutableLiveData<UIState<List<EWalletModel>>>(UIState(listOf()))
    private val _menuHomeLiveData = MutableLiveData<UIState<List<MenuModel>>>(UIState(listOf()))
    private val _savingDepositLiveData = MutableLiveData<UIState<List<SavingDepositModel>>>(
        UIState(listOf())
    )

    val eWalletLiveData: LiveData<UIState<List<EWalletModel>>> get() = _eWalletLiveData
    val savingDepositLiveData: MutableLiveData<UIState<List<SavingDepositModel>>> get() = _savingDepositLiveData
    val menuHomeLiveData: LiveData<UIState<List<MenuModel>>> get() = _menuHomeLiveData
    val isShowAllSavingLiveData: LiveData<Boolean> get() = _isShowAllSavingLiveData
    val isShowBalanceLiveData: LiveData<Boolean> get() = _isShowBalanceLiveData


    fun fetchEWallet() = viewModelScope.launch {
        _eWalletLiveData.postLoading()
        Handler(Looper.getMainLooper()).postDelayed({
            if (Math.random() > 0.4) {
                _eWalletLiveData.postSuccess(DummyDataGenerator.createEWalletDummyList())
                return@postDelayed
            }
            _eWalletLiveData.postError(Error("Intentionally set to error :)"))
        }, 2000)
    }

    fun fetchSavingDeposit() = viewModelScope.launch {
        _savingDepositLiveData.postLoading()
        Handler(Looper.getMainLooper()).postDelayed({
            if (Math.random() > 0.4) {
                _savingDepositLiveData.postSuccess(DummyDataGenerator.createSavingDepositDummyList())
                return@postDelayed
            }
            _savingDepositLiveData.postError(Error("Intentionally set to error :)"))
        }, 2000)
    }

    fun fetchMenuHome() = viewModelScope.launch {
        _menuHomeLiveData.postLoading()
        Handler(Looper.getMainLooper()).postDelayed({
            if (Math.random() > 0.4) {
                _menuHomeLiveData.postSuccess(DummyDataGenerator.createMenuDummyList())
                return@postDelayed
            }
            _menuHomeLiveData.postError(Error("Intentionally set to error :)"))
        }, 2000)
    }


    fun updateEWallet(selectedWallet: EWalletModel) {
        with(_eWalletLiveData) {
            value?.data?.forEach { w ->
                if (w.name == selectedWallet.name) {
                    w.isConnected = true
                    w.image = R.drawable.money_bag
                    w.balance = 100000.0
                }
            }
            postSuccess(_eWalletLiveData.value?.data!!)
        }

    }

    fun toggleShowBalance() {
        _isShowBalanceLiveData.apply {
            postValue(!value!!)
        }
    }
}