package com.example.pr56.data.remote

import androidx.lifecycle.LiveData
import com.example.pr56.data.local.MyDataDao
import com.example.pr56.data.local.MyDataModel
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MyRepository @Inject constructor(
    private val apiService: ApiService,
    private val myDataDao: MyDataDao
) {

    val allData: LiveData<List<MyDataModel>> = myDataDao.getAllData()

    suspend fun refreshData() {
        val dataFromApi = apiService.getData()
        myDataDao.insertAll(dataFromApi)
    }
}