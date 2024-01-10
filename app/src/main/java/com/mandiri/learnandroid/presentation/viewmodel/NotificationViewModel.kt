package com.mandiri.learnandroid.presentation.viewmodel

import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mandiri.learnandroid.helper.DummyDataGenerator
import com.mandiri.learnandroid.model.NotificationModel
import com.mandiri.learnandroid.utils.UIState
import com.mandiri.learnandroid.utils.extend.postError
import com.mandiri.learnandroid.utils.extend.postLoading
import com.mandiri.learnandroid.utils.extend.postSuccess
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class NotificationViewModel @Inject constructor() : ViewModel() {

    //    private lateinit var _notificationLiveData: MutableLiveData<List<NotificationModel>>
//    val notificationData: LiveData<List<NotificationModel>> get() = _notificationLiveData
//
//    fun setNotificationData(data: List<NotificationModel>) {
//        _notificationLiveData = MutableLiveData()
//        _notificationLiveData.postValue(data)
//    }

    private var _notificationLiveData: MutableLiveData<UIState<List<NotificationModel>>> =
        MutableLiveData(UIState(listOf()))

    val notificationData: LiveData<UIState<List<NotificationModel>>>
        get() = _notificationLiveData


//    fun getNotifications() {
//        _notificationLiveData.value?.status = Status.LOADING
//        _notificationLiveData.postValue(_notificationLiveData.value)
//
//        Handler(Looper.getMainLooper()).postDelayed({
//            if (Math.random() > 0.4) {
//                _notificationLiveData.value?.status = Status.SUCCESS
//                _notificationLiveData.value?.data = DummyDataGenerator.getNotificationData()
//            } else {
//                _notificationLiveData.value?.status = Status.ERROR
//                _notificationLiveData.value?.error = Error("Intentionally set to error :)")
//            }
//
//            _notificationLiveData.postValue(_notificationLiveData.value)
//        }, 2000)
//    }

    fun fetchNotification() {
        _notificationLiveData.postLoading()

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

}