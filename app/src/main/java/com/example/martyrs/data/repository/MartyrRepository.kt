package com.example.martyrs.data.repository

import com.example.martyrs.data.Martyr
import io.reactivex.Completable
import io.reactivex.Single

interface MartyrRepository {

    fun getMartyrs(
        pageSize: Int,
        pageNumber: Int,
        searchText: String?,
        sort: String
    ): Single<Martyr>

    //fun getMartyrsLocal(): Single<Martyr>

}