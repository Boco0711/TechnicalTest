package com.leprincesylvain.altentest.technicaltest.room

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.*
import com.leprincesylvain.altentest.technicaltest.data.model.Device
import com.leprincesylvain.altentest.technicaltest.data.model.User

@Dao
interface DAOAccess {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun InsertDevice(device: Device)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun InsertAllDevices(devices: List<Device>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun InsertUser(user: User)

    @Update
    suspend fun updateDevice(device: Device)

    @Update
    suspend fun updateUser(user: User)

    @Delete
    suspend fun deleteDevice(device: Device)

    /*
    @Query("SELECT * FROM Device_Table WHERE productType == productType")
    fun getDevicesByType(productType: ProductType) : MutableLiveData<MutableList<Device>>
     */

    @Query("SELECT * FROM Device_Table")
    fun getAllDevices(): LiveData<List<Device>>

}