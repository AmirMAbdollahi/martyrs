package com.example.martyrs.feature.Martyr

import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import com.example.martyrs.common.BaseViewModel
import com.example.martyrs.common.EXTRA_KEY_DATA
import com.example.martyrs.data.Comment
import com.example.martyrs.data.Data
import com.example.martyrs.data.DataComment
import com.example.martyrs.data.ResultComment
import com.example.martyrs.data.repository.CommentRepository
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import timber.log.Timber

class MartyrViewModel(bundle: Bundle, val commentRepository: CommentRepository) : BaseViewModel() {

    val martyrLiveData = MutableLiveData<Data>()
    val commentLiveData = MutableLiveData<List<DataComment>>()
    val totalCountLiveData = MutableLiveData<Int>()

    init {
        martyrLiveData.value = bundle.getParcelable(EXTRA_KEY_DATA)
        commentRepository.getComment(martyrLiveData.value!!.martyrId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : SingleObserver<Comment> {
                override fun onSubscribe(d: Disposable) {
                    compositeDisposable.add(d)
                }

                override fun onSuccess(t: Comment) {
                    commentLiveData.value = t.result.data
                    totalCountLiveData.value = t.result.totalCount
                }

                override fun onError(e: Throwable) {
                    Timber.e(e)
                }

            })

    }

}
