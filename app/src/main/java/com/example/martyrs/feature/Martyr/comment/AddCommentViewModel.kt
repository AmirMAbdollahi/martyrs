package com.example.martyrs.feature.Martyr.comment

import com.example.martyrs.common.BaseViewModel
import com.example.martyrs.common.MartyrCompletableObserver
import com.example.martyrs.common.MartyrExceptionMapper
import com.example.martyrs.data.ResultAddComment
import com.example.martyrs.data.repository.CommentRepository
import io.reactivex.Completable
import io.reactivex.CompletableObserver
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import org.greenrobot.eventbus.EventBus
import retrofit2.Call
import timber.log.Timber

class AddCommentViewModel(val commentRepository: CommentRepository) : BaseViewModel() {

    fun addComment(
        text: String,
        firstName: String,
        lastName: String,
        phoneNumber: String,
        martyrsId: Int
    ) {
        commentRepository.addComment(text,firstName,lastName,phoneNumber,martyrsId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : MartyrCompletableObserver(compositeDisposable) {
                override fun onComplete() {
                    Timber.i("comment added")
                }
            })
    }
}