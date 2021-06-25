package com.leprincesylvain.altentest.technicaltest.data.model

data class Device(
    val deviceName: String,
    val id: Int,
    val intensity: Int,
    var mode: String,
    val position: Int,
    val productType: String,
    val temperature: Int
)