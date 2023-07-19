package com.example.martyrs.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.example.martyrs.data.Data
import com.example.martyrs.data.Martyr
import io.reactivex.Completable
import io.reactivex.Single

@Dao
interface MartyrDao {

    @Transaction
    //@Query("SELECT * FROM martyrs")
    fun getMartyr(): Single<Martyr>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addMartyrs(martyr: Martyr): Completable

}