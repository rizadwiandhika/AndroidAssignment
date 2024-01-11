package com.mandiri.learnandroid.utils.extend

import androidx.lifecycle.MutableLiveData
import com.mandiri.learnandroid.constant.enums.UIStateStatus
import com.mandiri.learnandroid.utils.UIState

fun <T> MutableLiveData<UIState<T>>.postLoading() {
    postValue(UIState<T>(null).also {
        it.status = UIStateStatus.LOADING
        it.error = null
        value = it
    })
}

fun <T> MutableLiveData<UIState<T>>.postSuccess(incomingData: T) {
    if (value == null) {
        throw RuntimeException("value of MutableLiveData is null!")
    }
    with(value!!) {
        status = UIStateStatus.SUCCESS
        data = incomingData
        error = null
    }


    postValue(value)
}

fun <T> MutableLiveData<UIState<T>>.postError(err: Error) {
    if (value == null) {
        throw RuntimeException("value of MutableLiveData is null!")
    }
    with(value!!) {
        status = UIStateStatus.ERROR
        data = null
        error = err
    }

    postValue(value)
}