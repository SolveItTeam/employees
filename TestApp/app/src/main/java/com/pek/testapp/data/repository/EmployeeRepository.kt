package com.pek.testapp.data.repository

import androidx.lifecycle.LiveData
import com.pek.testapp.data.database.model.Employee
import com.pek.testapp.data.database.model.Speciality
import com.pek.testapp.data.network.model.EmployeeData

interface EmployeeRepository {

    suspend fun loadEmployees(): List<EmployeeData>

    suspend fun getAllEmployees(): List<Employee>

    suspend fun getEmployeesBySpeciality(specialityId: Long): List<Employee>

    suspend fun getSpecialitiesByEmployee(employeeId: Long): List<Speciality>

    suspend fun getEmployeeById(employeeId: Long): Employee

    fun getSpecialitiesLiveData(): LiveData<List<Speciality>>

}