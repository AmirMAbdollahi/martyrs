package com.example.martyrs.data.repository.DataSource

import com.example.martyrs.data.Martyr
import io.reactivex.Completable
import io.reactivex.Single

interface MartyrDataSource {

    fun getMartyrs(
        pageSize: Int,
        pageNumber: Int,
        searchText: String?,
        sort: String
    ): Single<Martyr>

    fun getLocalMartyrs(): Single<Martyr>

    fun addMartyrs(martyr: Martyr): Completable

}