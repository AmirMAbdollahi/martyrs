package com.example.martyrs.data.repository

import com.example.martyrs.data.Martyr
import com.example.martyrs.data.repository.DataSource.MartyrDataSource
import io.reactivex.Completable
import io.reactivex.Single

class MartyrRepositoryImpl(
    val martyrRemoteDataSource: MartyrDataSource,
    //val martyrLocalDataSource: MartyrDataSource
) : MartyrRepository {

    override fun getMartyrs(
        pageSize: Int,
        pageNumber: Int,
        searchText: String?,
        sort: String
    ): Single<Martyr> {
        return martyrRemoteDataSource.getMartyrs(pageSize, pageNumber, searchText, sort)
//            .doOnSuccess {
//                martyrLocalDataSource.addMartyrs(it)
//            }
    }

//    override fun getMartyrsLocal(): Single<Martyr> {
//        return martyrLocalDataSource.getLocalMartyrs()
//    }

}
