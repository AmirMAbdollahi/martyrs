package com.example.martyrs.data.repository

import com.example.martyrs.data.Comment
import com.example.martyrs.data.ResultAddComment
import io.reactivex.Completable
import io.reactivex.Single

interface CommentRepository {

    fun getComment(martyrId: Int): Single<Comment>

    fun addComment(
        text: String,
        firstName: String,
        lastName: String,
        phoneNumber: String,
        martyrId: Int
    ): Completable

}