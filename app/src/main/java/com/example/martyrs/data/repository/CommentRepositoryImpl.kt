package com.example.martyrs.data.repository

import com.example.martyrs.data.Comment
import com.example.martyrs.data.repository.DataSource.CommentDataSource
import io.reactivex.Single

class CommentRepositoryImpl(val commentDataSource: CommentDataSource) : CommentRepository {
    override fun getComment(martyrId: Int): Single<Comment> =
        commentDataSource.getComment(martyrId)

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