package com.pek.testapp.data.network.model

import com.google.gson.annotations.SerializedName
import com.pek.testapp.data.database.model.Speciality
import java.util.*

data class EmployeeData(
    @SerializedName("f_name")
    var firstName: String,

    @SerializedName("l_name")
    var lastName: String,

    var birthday: Date?,

    @SerializedName("avatr_url")
    var photoUrl: String?,

    @SerializedName("specialty")
    var specialities: List<Speciality>?
)