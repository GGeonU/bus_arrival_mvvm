package com.example.dell.mvvm_bus_arrival.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.dell.mvvm_bus_arrival.utils.Utils
import com.example.dell.mvvm_bus_arrival.model.Repository

@Suppress("UNCHECKED_CAST")
class ArsIdViewModelFactory(private val repository: Repository, private val utils: Utils): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ArsIdViewModel(repository, utils) as T
    }

}