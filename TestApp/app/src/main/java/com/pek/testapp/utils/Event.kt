package com.pek.testapp.utils

class Event<T>(data: T?) {

    var data = data
        get() {
            isConsumed = true
            return field
        }

    private var isConsumed = false

    fun isNotConsumed() = !isConsumed

}