package com.example.martyrs.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.martyrs.data.Data
import com.example.martyrs.data.Martyr
import com.example.martyrs.data.Result

//@Database(
//    entities = arrayOf(Martyr::class, Result::class, Data::class),
//    version = 1,
//    exportSchema = false
//)

abstract class AppDatabase : RoomDatabase() {

    abstract fun martyrDao(): MartyrDao
}