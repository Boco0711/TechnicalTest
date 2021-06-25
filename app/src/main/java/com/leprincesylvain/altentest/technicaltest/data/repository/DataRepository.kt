package com.leprincesylvain.altentest.technicaltest.data.repository

import com.leprincesylvain.altentest.technicaltest.data.network.DataApi

class DataRepository(
    private val api: DataApi
) : SafeApiRequest() {

    suspend fun getData() = apiRequest {
        println("Here we call to get data" + api.getData())
        api.getData() }
}