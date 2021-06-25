package com.leprincesylvain.altentest.technicaltest.ui.devices

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.leprincesylvain.altentest.technicaltest.data.repository.DataRepository

class UserViewModelFactory(
    private val repository: DataRepository
) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return UserViewModel(repository) as T
    }
}
