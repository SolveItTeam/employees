package com.pek.testapp.data.network

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*

object RetrofitFactory {

    private const val BASE_URL = "https://raw.githubusercontent.com/"

    fun buildRetrofitApi(): RetrofitApi {
        val httpClientBuilder = OkHttpClient.Builder()
        val loggingInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
        httpClientBuilder.addInterceptor(loggingInterceptor)

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(httpClientBuilder.build())
            .addConverterFactory(createGsonConverter())
            .build()
            .create(RetrofitApi::class.java)
    }

    private fun createGsonConverter() = GsonConverterFactory.create(GsonBuilder()
        .registerTypeAdapter(Date::class.java, DateTypeAdapter())
        .create())

}