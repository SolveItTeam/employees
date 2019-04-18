package com.pek.testapp.data.database.model

import androidx.room.Entity
import androidx.room.ForeignKey

@Entity(
    tableName = "employee_with_specialities",
    primaryKeys = ["employeeId", "specialityId"],
    foreignKeys = [
        ForeignKey(entity = Employee::class, parentColumns = ["id"], childColumns = ["employeeId"]),
        ForeignKey(entity = Speciality::class, parentColumns = ["id"], childColumns = ["specialityId"])
    ]
)
data class EmployeeWithSpeciality(
    var employeeId: Long,
    var specialityId: Long
)