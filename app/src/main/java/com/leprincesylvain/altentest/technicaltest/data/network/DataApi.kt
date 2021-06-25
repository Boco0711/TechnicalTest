package com.leprincesylvain.altentest.technicaltest.data.network

import com.leprincesylvain.altentest.technicaltest.data.model.DataResponse
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface DataApi {

    @GET("data.json")
    suspend fun getData(): Response<DataResponse>

    companion object {
        var retrofitService: DataApi? = null
        operator fun invoke(): DataApi {
            if (retrofitService == null) {
                retrofitService = Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl("http://storage42.com/modulotest/")
                    .build()
                    .create(DataApi::class.java)
            }
            return retrofitService!!
        }
    }
}