package com.pek.testapp.db

import com.pek.testapp.data.database.model.Employee
import java.util.*

fun createEmployee(
    id: Long,
    firstName: String,
    lastName: String,
    birthday: Date?,
    photo: String
) = Employee(id, firstName, lastName, birthday, photo)

fun createEmployees(
    count: Int, id: Long,
    firstName: String,
    lastName: String,
    birthday: Date?,
    photo: String
) = mutableListOf<Employee>().apply {
    for (i in 0 until count) {
        add(createEmployee(id, firstName, lastName, birthday, photo))
    }
}