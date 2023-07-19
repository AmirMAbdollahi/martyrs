package com.example.martyrs.data.repository.DataSource

import com.example.martyrs.data.Martyr
import com.example.martyrs.data.db.MartyrDao
import io.reactivex.Completable
import io.reactivex.Single


class MartyrLocalDataSource(val martyrDao: MartyrDao) : MartyrDataSource {

    override fun getMartyrs(
        pageSize: Int,
        pageNumber: Int,
        searchText: String?,
        sort: String
    ): Single<Martyr> {
        TODO("Not yet implemented")
    }

    override fun getLocalMartyrs(): Single<Martyr> {
        return martyrDao.getMartyr()
    }

    override fun addMartyrs(martyr: Martyr): Completable {
        return martyrDao.addMartyrs(martyr)
    }


}