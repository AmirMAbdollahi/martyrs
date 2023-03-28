package com.example.martyrs.data.repository

import com.example.martyrs.data.Martyr
import io.reactivex.Single

interface MartyrRepository {

    fun getMartyrs(pageSize: Int, pageNumber: Int): Single<Martyr>

}