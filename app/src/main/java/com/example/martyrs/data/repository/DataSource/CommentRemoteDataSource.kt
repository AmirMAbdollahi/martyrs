package com.example.martyrs.data.repository.DataSource

import com.example.martyrs.data.Comment
import com.example.martyrs.data.ResultAddComment
import com.example.martyrs.services.http.ApiService
import com.google.gson.JsonObject
import io.reactivex.Completable
import io.reactivex.Single
import retrofit2.Call
import retrofit2.Response

class CommentRemoteDataSource(val apiService: ApiService) : CommentDataSource {
    override fun getComment(martyrId: Int): Single<Comment> = apiService.getComment(martyrId)

    override fun addComment(
        text: String,
        firstName: String,
        lastName: String,
        phoneNumber: String,
        martyrId: Int
    ): Single<ResultAddComment> {
        return apiService.addComment(JsonObject().apply {
            addProperty("text", text)
            addProperty("firstName", firstName)
            addProperty("lastName", lastName)
            addProperty("phoneNumber", phoneNumber)
            addProperty("martyrId", martyrId)
        })
    }
}