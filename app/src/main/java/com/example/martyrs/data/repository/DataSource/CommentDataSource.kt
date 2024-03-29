package com.example.martyrs.data.repository.DataSource

import com.example.martyrs.data.Comment
import com.example.martyrs.data.ResultAddComment
import io.reactivex.Single

interface CommentDataSource {
    fun getComment(martyrId: Int): Single<Comment>

    fun addComment(
        text: String,
        firstName: String,
        lastName: String,
        phoneNumber: String,
        martyrId: Int
    ): Single<ResultAddComment>
}