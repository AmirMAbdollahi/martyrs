package com.example.martyrs.feature.common

import androidx.lifecycle.MutableLiveData
import com.example.martyrs.R
import com.example.martyrs.common.BaseViewModel
import com.example.martyrs.common.MartyrSingleObserver
import com.example.martyrs.data.EmptyState
import com.example.martyrs.data.Martyr
import com.example.martyrs.data.Sort
import com.example.martyrs.data.repository.MartyrRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MartyrsViewModel(var sort: Int, val martyrRepository: MartyrRepository) :
    BaseViewModel() {

    val martyrLiveData = MutableLiveData<Martyr>()
    val martyrEmptyStateLiveData = MutableLiveData<EmptyState>()
    val selectedSortTitleLiveData = MutableLiveData<Int>()

    val sortTitle = arrayOf(
        R.string.Null,
        R.string.minAge,
        R.string.maxAge,
        R.string.firstNameSort,
        R.string.lastNameSort
    )

//    val sortRequest = arrayOf(
//        "",
//        "MinAge",
//        "MaxAge",
//        "FirstName",
//        "LastName"
//    )

    init {
        getOrSearchMartyr(null)
        martyrEmptyStateLiveData.value = EmptyState(false)
        selectedSortTitleLiveData.value = sortTitle[0]
    }

    //sortRequest[sort]
    fun getOrSearchMartyr(query: String?) {
        var sortRequest = Sort.values()
        var sortString = sortRequest[sort].toString()
        if (sortRequest[sort].toString() == "NONE") {
            sortString = ""
        }
        progressBarLiveData.value = true
        // TODO: back here (pagination)
        martyrRepository.getMartyrs(150, 1, query, sortString)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doFinally { progressBarLiveData.value = false }
            .subscribe(object : MartyrSingleObserver<Martyr>(compositeDisposable) {
                override fun onSuccess(t: Martyr) {
//                    if (!query.isNullOrEmpty() && !t.result.data.isNullOrEmpty()) {
//                        //type something
//                        martyrEmptyStateLiveData.value = EmptyState(false)
//                        martyrLiveData.value = t
//                    } else if (!query.isNullOrEmpty() && t.result.data.isNullOrEmpty()) {
//                        //type something that is not exist
//                        martyrEmptyStateLiveData.value =
//                            EmptyState(true, R.string.searchMartyrNotExist)
//                    } else {
//                        //get martyrs
//                        martyrEmptyStateLiveData.value = EmptyState(false)
//                        martyrLiveData.value = t
//                    }
                    if (!t.result.data.isNullOrEmpty()) {
                        martyrEmptyStateLiveData.value = EmptyState(false)
                        martyrLiveData.value = t
                    } else {
                        martyrEmptyStateLiveData.value =
                            EmptyState(true, R.string.searchMartyrNotExist)
                    }
                }
            })
    }

//    val martyrLocalLiveData = MutableLiveData<Martyr>()
//
//    fun getLocalMartyrs() {
//        martyrRepository.getMartyrsLocal().subscribeOn(Schedulers.io())
//            .observeOn(AndroidSchedulers.mainThread()).subscribe(object : SingleObserver<Martyr> {
//                override fun onSubscribe(d: Disposable) {
//                    TODO("Not yet implemented")
//                }
//
//                override fun onSuccess(t: Martyr) {
//                    martyrLocalLiveData.value = t
//                }
//
//                override fun onError(e: Throwable) {
//                    TODO("Not yet implemented")
//                }
//
//            })
//    }

    fun onSelectedSortChangeByUser(sort: Int, query: String?) {
        this.sort = sort
        this.selectedSortTitleLiveData.value = sortTitle[sort]
        getOrSearchMartyr(query)
    }

}