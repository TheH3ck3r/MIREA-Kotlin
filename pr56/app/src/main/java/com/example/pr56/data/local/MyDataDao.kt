package com.example.pr56.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface MyDataDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(data: List<MyDataModel>)

    @Query("SELECT * FROM my_data_table")
    fun getAllData(): LiveData<List<MyDataModel>>
}