package com.pek.testapp.ui.base

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import timber.log.Timber
import java.lang.Exception

abstract class BaseViewModel(application: Application): AndroidViewModel(application) {

    val errorLiveData = MutableLiveData<Exception>()

    protected fun handleError(e: Exception) {
        errorLiveData.postValue(e)
    }

}