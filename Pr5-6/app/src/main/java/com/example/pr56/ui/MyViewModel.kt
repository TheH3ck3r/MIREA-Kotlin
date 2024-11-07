package com.example.pr56.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pr56.data.local.MyDataModel
import com.example.pr56.data.remote.MyRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MyViewModel @Inject constructor(
    private val repository: MyRepository
) : ViewModel() {

    val data: LiveData<List<MyDataModel>> = repository.allData

    fun refreshData() {
        viewModelScope.launch {
            repository.refreshData()
        }
    }
}