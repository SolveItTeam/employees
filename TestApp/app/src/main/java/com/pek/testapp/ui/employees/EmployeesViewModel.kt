package com.pek.testapp.ui.employees

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.pek.testapp.data.database.model.Employee
import com.pek.testapp.data.repository.EmployeeRepository
import com.pek.testapp.ui.base.BaseViewModel
import com.pek.testapp.ui.base.adapter.BaseItem
import com.pek.testapp.utils.Event
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class EmployeesViewModel(
    application: Application,
    private val employeesRepository: EmployeeRepository
) : BaseViewModel(application) {

    private var specialityId: Long? = null

    val employeesLiveData = MutableLiveData<List<Employee>>()

    val openEmployeesLiveData = MutableLiveData<Event<Long>>()

    fun loadEmployees() {
        GlobalScope.launch {
            val employees = if (specialityId == null) {
                employeesRepository.getAllEmployees()
            } else {
                employeesRepository.getEmployeesBySpeciality(specialityId!!)
            }

            launch (Dispatchers.Main) {
                employeesLiveData.value = employees
            }
        }
    }

    fun updateSpecialityId(specialityId: Long) {
        this.specialityId = specialityId
    }

    fun onEmployeeClick(item: BaseItem) {
        if (item is Employee) {
            openEmployeesLiveData.value = Event(item.id)
        }
    }

}