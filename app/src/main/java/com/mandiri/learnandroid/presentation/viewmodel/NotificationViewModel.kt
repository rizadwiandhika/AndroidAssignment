package com.mandiri.learnandroid.presentation.viewmodel

import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mandiri.learnandroid.model.NotificationModel
import com.mandiri.learnandroid.utils.DummyDataGenerator
import com.mandiri.learnandroid.utils.UIState
import com.mandiri.learnandroid.utils.extend.postError
import com.mandiri.learnandroid.utils.extend.postLoading
import com.mandiri.learnandroid.utils.extend.postSuccess
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NotificationViewModel @Inject constructor() : ViewModel() {

    private var _notificationLiveData: MutableLiveData<UIState<List<NotificationModel>>> =
        MutableLiveData(UIState(listOf()))

    val notificationData: LiveData<UIState<List<NotificationModel>>>
        get() = _notificationLiveData

    fun fetchNotification() = viewModelScope.launch {
        _notificationLiveData.postLoading()

        val status = test()
        Handler(Looper.getMainLooper()).postDelayed({
            val random = Math.random()
            Log.d("MY_DEBUG", "Random = $random")
            if (random > 0.4) {
                _notificationLiveData.postSuccess(DummyDataGenerator.getNotificationData())
            } else {
                _notificationLiveData.postError(Error("Intentionally set to error :)"))
            }
        }, 2000)
    }

    private suspend fun test(): String {
        delay(2000)
        return "done"
    }

}