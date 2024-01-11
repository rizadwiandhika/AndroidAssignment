package com.mandiri.learnandroid.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mandiri.learnandroid.constant.enums.LoginStatus
import com.mandiri.learnandroid.constant.enums.LoginStatus.ALREADY_LOGGED_IN
import com.mandiri.learnandroid.constant.enums.LoginStatus.EMPTY
import com.mandiri.learnandroid.constant.enums.LoginStatus.INVALID_CREDENTIAL
import com.mandiri.learnandroid.constant.enums.LoginStatus.SUCCESS
import com.mandiri.learnandroid.utils.SharedPreferenceHelper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.util.UUID
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val preferences: SharedPreferenceHelper) :
    ViewModel() {

    companion object {
        const val VALID_PASSWORD = "password"
    }

    private val _authenticationLiveData = MutableLiveData<LoginStatus?>(null)

    val authenticationLiveData: LiveData<LoginStatus?> get() = _authenticationLiveData

    fun checkIfHasBeenAuthenticated() = viewModelScope.launch {
        if (preferences.getToken() != "") {
            _authenticationLiveData.postValue(ALREADY_LOGGED_IN)
        }
    }

    fun authenticate(password: String) = viewModelScope.launch {
        if (password.isEmpty()) {
            _authenticationLiveData.postValue(EMPTY)
            return@launch
        }

        if (password != VALID_PASSWORD) {
            _authenticationLiveData.postValue(INVALID_CREDENTIAL)
            return@launch
        }

        preferences.saveToken(UUID.randomUUID().toString())
        _authenticationLiveData.postValue(SUCCESS)
    }

}