package com.example.martyrs.data.repository.DataSource

import com.example.martyrs.data.Martyr
import com.example.martyrs.services.http.ApiService
import io.reactivex.Single

class MartyrRemoteDataSource(val apiService: ApiService) : MartyrDataSource {

    override fun getMartyrs(pageSize: Int, pageNumber: Int, searchText: String?): Single<Martyr> =
        apiService.getMartyrs(pageSize, pageNumber, searchText)

}