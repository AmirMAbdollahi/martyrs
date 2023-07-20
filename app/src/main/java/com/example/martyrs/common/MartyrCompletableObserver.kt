package com.example.martyrs.common

import io.reactivex.CompletableObserver
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import org.greenrobot.eventbus.EventBus

abstract class MartyrCompletableObserver(val compositeDisposable: CompositeDisposable):CompletableObserver{
    override fun onSubscribe(d: Disposable) {
        compositeDisposable.add(d)
    }

    override fun onError(e: Throwable) {
        EventBus.getDefault().post(MartyrExceptionMapper.map(e))
    }

}