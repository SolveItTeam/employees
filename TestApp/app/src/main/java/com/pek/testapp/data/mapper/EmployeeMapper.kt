package com.pek.testapp.data.mapper

import com.pek.testapp.data.database.model.Employee
import com.pek.testapp.data.database.model.Speciality
import com.pek.testapp.data.network.model.EmployeeData
import com.pek.testapp.utils.TextUtils

object EmployeeMapper {

    fun mapToEmployeeData(employee: Employee, specialities: List<Speciality>): EmployeeData =
        EmployeeData(
            employee.firstName,
            employee.lastName,
            employee.birthday,
            employee.photo,
            specialities
        )

    fun mapToEmployee(employeeData: EmployeeData) = Employee(
        0,
        TextUtils.upFirstLetter(employeeData.firstName),
        TextUtils.upFirstLetter(employeeData.lastName),
        employeeData.birthday,
        employeeData.photoUrl
    )

}