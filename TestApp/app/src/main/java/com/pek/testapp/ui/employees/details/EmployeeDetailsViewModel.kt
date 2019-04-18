package com.pek.testapp.ui.employees.details

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.pek.testapp.data.mapper.EmployeeMapper
import com.pek.testapp.data.network.model.EmployeeData
import com.pek.testapp.data.repository.EmployeeRepository
import com.pek.testapp.ui.base.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class EmployeeDetailsViewModel(
    application: Application,
    private val employeeRepository: EmployeeRepository
) : BaseViewModel(application) {

    private var employeeId: Long? = null

    val employeeDataLiveData = MutableLiveData<EmployeeData>()

    fun loadEmployee() {
        employeeId?.let { validId ->
            GlobalScope.launch {
                val employee = employeeRepository.getEmployeeById(validId)
                val specialities = employeeRepository.getSpecialitiesByEmployee(employee.id)
                val employeeData = EmployeeMapper.mapToEmployeeData(employee, specialities)

                launch(Dispatchers.Main) {
                    employeeDataLiveData.value = employeeData
                }
            }
        }
    }

    fun updateEmployeeId(employeeId: Long) {
        this.employeeId = employeeId
    }

}