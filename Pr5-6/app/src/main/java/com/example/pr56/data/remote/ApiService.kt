package com.example.pr56.data.remote

import com.example.pr56.data.local.MyDataModel
import retrofit2.http.GET

interface ApiService {
    @GET("data-endpoint")
    suspend fun getData(): List<MyDataModel>
}