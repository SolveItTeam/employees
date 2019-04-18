package com.pek.testapp.data.network

import com.pek.testapp.data.network.model.EmployeeResponse
import retrofit2.Call
import retrofit2.http.GET

interface RetrofitApi {

    @GET("/SolveItTeam/employees/master/test.json")
    fun getEmployees(): Call<EmployeeResponse>

}