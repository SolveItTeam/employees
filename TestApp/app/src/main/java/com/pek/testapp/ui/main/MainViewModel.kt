package com.pek.testapp.ui.main

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.pek.testapp.data.repository.EmployeeRepository
import com.pek.testapp.ui.base.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import timber.log.Timber
import java.lang.Exception

class MainViewModel(
    application: Application,
    private val employeeRepository: EmployeeRepository
) : BaseViewModel(application) {

    val loadingStateLiveData = MutableLiveData<Boolean>()

    private var job: Job? = null

    fun loadEmployees() {
        job?.let {
            if (it.isActive || it.isCompleted) return
        }

        loadingStateLiveData.value = true

        job = GlobalScope.launch {
            try {
                employeeRepository.loadEmployees()

                launch(Dispatchers.Main) {
                    loadingStateLiveData.value = false
                }
            } catch (e: Exception) {
                Timber.e(e)
                loadingStateLiveData.postValue(false)
                handleError(e)
            }
        }
    }

    override fun onCleared() {
        job?.cancel()
        super.onCleared()
    }

}