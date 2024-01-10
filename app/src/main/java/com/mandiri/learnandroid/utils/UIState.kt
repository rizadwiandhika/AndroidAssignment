package com.mandiri.learnandroid.utils

class UIState<T>(private var _data: T?) {
    private var _error: Error? = null
    private var _status: Status = Status.SUCCESS

    
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

    var status: Status
        get() = _status
        set(value) {
            _status = value
        }


}

enum class Status {
    LOADING, SUCCESS, ERROR
}