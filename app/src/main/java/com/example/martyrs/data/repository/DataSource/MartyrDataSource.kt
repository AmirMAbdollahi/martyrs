package com.example.martyrs.data.repository.DataSource

import com.example.martyrs.data.Martyr
import io.reactivex.Single

interface MartyrDataSource {

    fun getMartyrs(pageSize: Int, pageNumber: Int): Single<Martyr>

}