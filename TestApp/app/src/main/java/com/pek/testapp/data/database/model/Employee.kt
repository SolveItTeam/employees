package com.pek.testapp.data.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.pek.testapp.ui.base.adapter.BaseItem
import java.util.*

@Entity(tableName = "employees")
data class Employee(
    @PrimaryKey(autoGenerate = true)
    var id: Long,
    var firstName: String,
    var lastName: String,
    var birthday: Date?,
    var photo: String?
) : BaseItem