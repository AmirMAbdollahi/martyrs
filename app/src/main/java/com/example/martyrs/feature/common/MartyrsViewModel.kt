package com.example.martyrs.feature.common

import androidx.lifecycle.MutableLiveData
import com.example.martyrs.R
import com.example.martyrs.common.BaseViewModel
import com.example.martyrs.data.EmptyState
import com.example.martyrs.data.Martyr
import com.example.martyrs.data.repository.MartyrRepository
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import timber.log.Timber

class MartyrsViewModel(val martyrRepository: MartyrRepository) :
    BaseViewModel() {

    val martyrLiveData = MutableLiveData<Martyr>()
    val martyrEmptyStateLiveData = MutableLiveData<EmptyState>()
    val sortTitle = arrayOf(
        R.string.minAge,
        R.string.maxAge,
        R.string.lastNameSort
    )

    init {
        getOrSearchMartyr(null)
        martyrEmptyStateLiveData.value = EmptyState(false)
    }

    fun getOrSearchMartyr(query: String?) {
        progressBarLiveData.value = true
        // TODO: back here (pagination)
        martyrRepository.getMartyrs(150, 1, query)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doFinally { progressBarLiveData.value = false }
            .subscribe(object : SingleObserver<Martyr> {
                override fun onSubscribe(d: Disposable) {
                    compositeDisposable.add(d)
                }

                override fun onSuccess(t: Martyr) {
                    if (!query.isNullOrEmpty() && !t.result.data.isNullOrEmpty()) {
                        //type something
                        martyrEmptyStateLiveData.value = EmptyState(false)
                        martyrLiveData.value = t
                    } else if (!query.isNullOrEmpty() && t.result.data.isNullOrEmpty()) {
                        //type something that is not exist
                        martyrEmptyStateLiveData.value =
                            EmptyState(true, R.string.searchMartyrNotExist)
                    } else {
                        //get martyrs
                        martyrEmptyStateLiveData.value = EmptyState(false)
                        martyrLiveData.value = t
                    }
                }

                override fun onError(e: Throwable) {
                    Timber.e(e)
                }

            })
    }

//    fun onSelectedSortChangeByUser(sort:Int){
//        this.sort = sort
//        this.selectedSortTitleLiveData.value = sortTitle[sort]
//        getProducts()
//    }

}