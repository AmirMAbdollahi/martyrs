package com.example.martyrs.feature.main

import androidx.lifecycle.MutableLiveData
import com.example.martyrs.common.BaseViewModel
import com.example.martyrs.data.Martyr
import com.example.martyrs.data.repository.MartyrRepository
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import timber.log.Timber

class MainViewModel(martyrRepository: MartyrRepository) : BaseViewModel() {

    val martyrLiveData = MutableLiveData<Martyr>()

    init {
        progressBarLiveData.value = true
        martyrRepository.getMartyrs(150, 1)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doFinally { progressBarLiveData.value = false }
            .subscribe(object : SingleObserver<Martyr> {
                override fun onSubscribe(d: Disposable) {
                    compositeDisposable.add(d)
                }

                override fun onSuccess(t: Martyr) {
                    martyrLiveData.value = t
                }

                override fun onError(e: Throwable) {
                    Timber.e(e)
                }

            })

    }

}