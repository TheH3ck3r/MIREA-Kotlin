package com.example.pr56.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "my_data_table")
data class MyDataModel(
    @PrimaryKey val id: Int,
    val name: String,
    val value: String
)