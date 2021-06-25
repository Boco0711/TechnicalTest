package com.leprincesylvain.altentest.technicaltest.ui.devices

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.leprincesylvain.altentest.technicaltest.data.repository.DataRepository
import com.leprincesylvain.altentest.technicaltest.utils.Coroutines

class DevicesViewModelFactory(
    private val repository: DataRepository
) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return DevicesViewModel(repository) as T
    }

}