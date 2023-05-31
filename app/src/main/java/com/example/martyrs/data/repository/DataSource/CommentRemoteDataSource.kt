package com.example.martyrs.data.repository.DataSource

import com.example.martyrs.data.Comment
import com.example.martyrs.services.http.ApiService
import io.reactivex.Single

class CommentRemoteDataSource(val apiService: ApiService) : CommentDataSource {
    override fun getComment(martyrId: Int): Single<Comment> = apiService.getComment(martyrId)

    override fun addComment(
        text: String,
        firstName: String,
        lastName: String,
        phoneNumber: Int,
        martyrId: Int
    ) {
        TODO("Not yet implemented")
    }
}