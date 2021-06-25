package com.leprincesylvain.altentest.technicaltest.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import androidx.room.TypeConverters

@Entity(tableName = "User_Table")
data class User(
    @PrimaryKey(autoGenerate = true)
    val id: Int,

    @TypeConverters(Converters::class)
    val address: Address,

    val birthDate: Long,
    val firstName: String,
    val lastName: String
)