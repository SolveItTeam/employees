package com.pek.testapp.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.pek.testapp.data.database.model.Employee
import com.pek.testapp.data.database.model.EmployeeWithSpeciality
import com.pek.testapp.data.database.model.Speciality

@Dao
interface EmployeeWithSpecialityDao {

    @Insert
    fun insert(employeeWithSpeciality: EmployeeWithSpeciality)

    @Query("""SELECT * FROM employees INNER JOIN employee_with_specialities ON
            employees.id = employee_with_specialities.employeeId
            WHERE employee_with_specialities.specialityId = :specialityId"""
    )
    fun getEmployeesForSpeciality(specialityId: Long): List<Employee>

    @Query("""SELECT * FROM specialities INNER JOIN employee_with_specialities ON
            specialities.id = employee_with_specialities.specialityId
            WHERE employee_with_specialities.employeeId = :employeeId"""
    )
    fun getSpecialitiesForEmployee(employeeId: Long): List<Speciality>

    @Query("DELETE FROM employee_with_specialities")
    fun clear()

}