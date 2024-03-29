package com.example.martyrs.data.repository.DataSource

import com.example.martyrs.data.Martyr
import com.example.martyrs.services.http.ApiService
import io.reactivex.Completable
import io.reactivex.Single

class MartyrRemoteDataSource(val apiService: ApiService) : MartyrDataSource {

    override fun getMartyrs(
        pageSize: Int,
        pageNumber: Int,
        searchText: String?,
        sort: String
    ): Single<Martyr> =
        apiService.getMartyrs(pageSize, pageNumber, searchText, sort)

    override fun getLocalMartyrs(): Single<Martyr> {
        TODO("Not yet implemented")
    }

    override fun addMartyrs(martyr: Martyr): Completable {
        TODO("Not yet implemented")
    }


}