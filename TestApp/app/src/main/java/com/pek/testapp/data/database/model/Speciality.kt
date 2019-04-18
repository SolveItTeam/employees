package com.pek.testapp.data.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import com.pek.testapp.ui.base.adapter.BaseItem

@Entity(tableName = "specialities")
data class Speciality(
    @PrimaryKey
    @SerializedName("specialty_id")
    var id: Long,
    var name: String
) : BaseItem