package com.pek.testapp.data.repository

import androidx.lifecycle.LiveData
import com.pek.testapp.data.database.EmployeesDatabase
import com.pek.testapp.data.database.model.Employee
import com.pek.testapp.data.database.model.EmployeeWithSpeciality
import com.pek.testapp.data.database.model.Speciality
import com.pek.testapp.data.mapper.EmployeeMapper
import com.pek.testapp.data.network.model.EmployeeData
import com.pek.testapp.data.network.model.EmployeeResponse
import com.pek.testapp.data.network.RetrofitApi
import com.pek.testapp.utils.bodyOrError
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class EmployeeRepositoryImpl(
    private val api: RetrofitApi,
    private val database: EmployeesDatabase
) : EmployeeRepository {

    override suspend fun loadEmployees(): List<EmployeeData> = withContext(Dispatchers.Default) {
        val employeeResponse = api.getEmployees().execute().bodyOrError()
        saveToDatabase(employeeResponse!!)
        return@withContext employeeResponse.response
    }

    private fun saveToDatabase(employeeResponse: EmployeeResponse) {
        database.getEmployeesWithSpecialitiesDao().clear()
        database.getEmployeeDao().clear()
        database.getSpecialityDao().clear()

        val specialitiesToSave = mutableListOf<Speciality>()
        employeeResponse.response.forEach {
            it.specialities?.let { specialities ->
                specialities.forEach { speciality ->
                    if (!specialitiesToSave.contains(speciality)) {
                        specialitiesToSave.add(speciality)
                    }
                }
            }
        }

        database.getSpecialityDao().insert(specialitiesToSave)

        employeeResponse.response.forEach {
            val employee = EmployeeMapper.mapToEmployee(it)

            val id = database.getEmployeeDao().insert(employee)

            it.specialities?.forEach { speciality ->
                database.getEmployeesWithSpecialitiesDao().insert(EmployeeWithSpeciality(id, speciality.id))
            }

        }
    }

    override suspend fun getAllEmployees(): List<Employee> = withContext(Dispatchers.Default) {
        database.getEmployeeDao().getAllSync()
    }

    override suspend fun getEmployeesBySpeciality(specialityId: Long): List<Employee> =
        withContext(Dispatchers.Default) {
            database.getEmployeesWithSpecialitiesDao().getEmployeesForSpeciality(specialityId)
        }

    override suspend fun getSpecialitiesByEmployee(employeeId: Long): List<Speciality> =
        withContext(Dispatchers.Default) {
            database.getEmployeesWithSpecialitiesDao().getSpecialitiesForEmployee(employeeId)
        }

    override suspend fun getEmployeeById(employeeId: Long): Employee = withContext(Dispatchers.Default) {
        database.getEmployeeDao().getEmployeeByIdSync(employeeId)
    }

    override fun getSpecialitiesLiveData(): LiveData<List<Speciality>> =
        database.getSpecialityDao().getAllSpecialitiesLiveData()


}