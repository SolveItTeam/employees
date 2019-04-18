package com.pek.testapp.utils

import com.pek.testapp.data.exception.ApiException
import retrofit2.Response


fun <T> Response<T>.bodyOrError(): T {
    val responseCode = code()

    val body = body()
    if (isSuccessful && body != null) {
        return body
    }

    throw ApiException(responseCode)

}