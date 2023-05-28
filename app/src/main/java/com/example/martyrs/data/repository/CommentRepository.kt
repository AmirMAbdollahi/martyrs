package com.example.martyrs.data.repository

import com.example.martyrs.data.ResultComment
import io.reactivex.Single

interface CommentRepository {

    fun getComment(martyrId: Int): Single<ResultComment>

    fun addComment()

}