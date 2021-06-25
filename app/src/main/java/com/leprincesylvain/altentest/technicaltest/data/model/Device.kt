package com.leprincesylvain.altentest.technicaltest.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Device_Table")
data class Device(
    val deviceName: String,
    @PrimaryKey
    val id: Int,
    val intensity: Int?,
    var mode: String?,
    val position: Int?,
    val productType: String,
    val temperature: Int?
)