package com.leprincesylvain.altentest.technicaltest.data.repository

import androidx.room.Dao
import com.leprincesylvain.altentest.technicaltest.data.model.Device
import com.leprincesylvain.altentest.technicaltest.data.model.User
import com.leprincesylvain.altentest.technicaltest.data.network.DataApi
import com.leprincesylvain.altentest.technicaltest.room.DAOAccess

class DataRepository(
    private val dao: DAOAccess,
    private val api: DataApi
) : SafeApiRequest() {


    val devices = dao.getAllDevices()

    suspend fun insertDevice(device: Device) {
        dao.InsertDevice(device)
    }

    suspend fun insertAllDevice(devices: List<Device>) {
        dao.InsertAllDevices(devices)
    }

    suspend fun updateDevice(device: Device) {
        dao.updateDevice(device)
    }

    suspend fun deleteDevice(device: Device) {
        dao.deleteDevice(device)
    }

    suspend fun updateUser(user: User) {
        dao.updateUser(user)
    }

    suspend fun getData() = apiRequest {
        api.getData() }
}