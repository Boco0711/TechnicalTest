package com.leprincesylvain.altentest.technicaltest.ui.devices

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.leprincesylvain.altentest.technicaltest.data.model.Device
import com.leprincesylvain.altentest.technicaltest.data.model.User
import com.leprincesylvain.altentest.technicaltest.data.repository.DataRepository
import com.leprincesylvain.altentest.technicaltest.utils.Coroutines
import kotlinx.coroutines.Job

class UserViewModel(private val repository: DataRepository)  : ViewModel() {
    private lateinit var job: Job
    private val _user = MutableLiveData<User>()
    val user: LiveData<User>
        get() = _user

    fun getUser() {
        //println("here we get new data" + devices.value!![0].deviceName)
        job = Coroutines.ioThenMain(
            {
                repository.getData().user
            },
            { _user.value = it }
        )
    }

    override fun onCleared() {
        super.onCleared()
        if (::job.isInitialized) job.cancel()
    }
}