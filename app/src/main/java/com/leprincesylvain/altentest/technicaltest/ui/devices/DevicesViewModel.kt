package com.leprincesylvain.altentest.technicaltest.ui.devices

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.leprincesylvain.altentest.technicaltest.data.model.Device
import com.leprincesylvain.altentest.technicaltest.data.repository.DataRepository
import com.leprincesylvain.altentest.technicaltest.utils.Coroutines
import kotlinx.coroutines.Job

class DevicesViewModel(
    private val repository: DataRepository
) : ViewModel() {

    private lateinit var job: Job

    private val _devices = MutableLiveData<MutableList<Device>>()
    val devices: LiveData<MutableList<Device>>
        get() = _devices

    fun getDevices() {
        //println("here we get new data" + devices.value!![0].deviceName)
        if(repository.devices.value == null) {
            println("here _device is null " + (devices.value?.size ?: String()))

            job = Coroutines.ioThenMain(
                {
                    repository.getData().devices
                },
                { _devices.value = it as MutableList<Device>}
            )
            insertAllDevice()
        }
    }

    fun insertAllDevice() {
        println("here we insert data")
        job = Coroutines.ioThenMain({
            println("here Fire")
            repository.insertAllDevice(repository.getData().devices)
            println("here Fire too")
        },
            {}
            //{ _devices.value = it as MutableList<Device>}
        )
    }

    override fun onCleared() {
        super.onCleared()
        if (::job.isInitialized) job.cancel()
    }
}