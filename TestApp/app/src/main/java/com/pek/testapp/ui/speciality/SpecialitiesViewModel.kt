package com.pek.testapp.ui.speciality

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.pek.testapp.data.database.model.Speciality
import com.pek.testapp.data.repository.EmployeeRepository
import com.pek.testapp.ui.base.BaseViewModel
import com.pek.testapp.ui.base.adapter.BaseItem
import com.pek.testapp.utils.Event

class SpecialitiesViewModel(
    application: Application,
    private val employeeRepository: EmployeeRepository
) : BaseViewModel(application) {

    val specialitiesLiveData = employeeRepository.getSpecialitiesLiveData()

    val openEmployeesBySpecialityEvent = MutableLiveData<Event<Long>>()

    fun onSpecialityClick(item: BaseItem) {
        if (item is Speciality) {
            openEmployeesBySpecialityEvent.value = Event(item.id)
        }
    }

}