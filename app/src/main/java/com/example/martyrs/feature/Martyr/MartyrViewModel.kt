package com.example.martyrs.feature.Martyr

import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import com.example.martyrs.R
import com.example.martyrs.common.BaseViewModel
import com.example.martyrs.common.EXTRA_KEY_DATA
import com.example.martyrs.data.Comment
import com.example.martyrs.data.Data
import com.example.martyrs.data.DataComment
import com.example.martyrs.data.EmptyState
import com.example.martyrs.data.repository.CommentRepository
import com.google.android.material.textfield.TextInputEditText
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import retrofit2.Response
import timber.log.Timber

class MartyrViewModel(bundle: Bundle, val commentRepository: CommentRepository) : BaseViewModel() {

    val martyrLiveData = MutableLiveData<Data>()
    val commentLiveData = MutableLiveData<List<DataComment>>()
    val totalCountLiveData = MutableLiveData<Int>()
    val commentEmptyState = MutableLiveData<EmptyState>()

    init {
        martyrLiveData.value = bundle.getParcelable(EXTRA_KEY_DATA)
        getComment()
    }

    fun getComment() {
        commentRepository.getComment(martyrLiveData.value!!.martyrId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : SingleObserver<Comment> {
                override fun onSubscribe(d: Disposable) {
                    compositeDisposable.add(d)
                }

                override fun onSuccess(t: Comment) {
                    if (t.result.totalCount == 0) {
                        commentEmptyState.value = EmptyState(true, R.string.commentNotExist)
                    } else {
                        commentLiveData.value = t.result.data
                        commentEmptyState.value = EmptyState(false)
                    }
                    totalCountLiveData.value = t.result.totalCount
                }

                override fun onError(e: Throwable) {
                    Timber.e(e)
                }

            })
    }


}
