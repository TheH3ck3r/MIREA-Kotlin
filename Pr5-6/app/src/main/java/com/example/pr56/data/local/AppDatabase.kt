package com.example.pr56.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [MyDataModel::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun myDataDao(): MyDataDao
}
