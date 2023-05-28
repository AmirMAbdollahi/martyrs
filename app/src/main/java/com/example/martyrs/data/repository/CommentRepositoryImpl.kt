package com.example.martyrs.data.repository

import com.example.martyrs.data.ResultComment
import io.reactivex.Single

class CommentRepositoryImpl:CommentRepository {
    override fun getComment(martyrId: Int): Single<ResultComment> {
        TODO("Not yet implemented")
    }

    override fun addComment() {
        TODO("Not yet implemented")
    }
}