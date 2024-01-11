package com.mandiri.learnandroid.utils

import com.mandiri.learnandroid.constant.enums.UIStateStatus
import com.mandiri.learnandroid.constant.enums.UIStateStatus.LOADING

class UIState<T>(private var _data: T?) {
    private var _error: Error? = null
    private var _status: UIStateStatus = LOADING


    var data: T?
        get() = _data
        set(value) {
            _data = value
        }

    var error: Error?
        get() = _error
        set(value) {
            _error = value
        }

    var status: UIStateStatus
        get() = _status
        set(value) {
            _status = value
        }


}
