package com.example.martyrs.data.repository

import com.example.martyrs.data.Comment
import io.reactivex.Single

interface CommentRepository {

    fun getComment(martyrId: Int): Single<Comment>

    fun addComment(
        text: String,
        firstName: String,
        lastName: String,
        phoneNumber: Int,
        martyrId: Int
    )

}