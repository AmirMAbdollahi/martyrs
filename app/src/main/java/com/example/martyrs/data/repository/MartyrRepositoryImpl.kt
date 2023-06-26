package com.example.martyrs.data.repository

import com.example.martyrs.data.Martyr
import com.example.martyrs.data.repository.DataSource.MartyrDataSource
import io.reactivex.Single

class MartyrRepositoryImpl(val martyrDataSource: MartyrDataSource) : MartyrRepository {

    override fun getMartyrs(pageSize: Int, pageNumber: Int, searchText: String?): Single<Martyr> =
        martyrDataSource.getMartyrs(pageSize, pageNumber, searchText)

}