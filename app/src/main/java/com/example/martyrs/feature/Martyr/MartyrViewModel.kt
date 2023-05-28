package com.example.martyrs.feature.Martyr

import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import com.example.martyrs.common.BaseViewModel
import com.example.martyrs.common.EXTRA_KEY_DATA
import com.example.martyrs.data.Data

class MartyrViewModel(bundle: Bundle) : BaseViewModel() {

    val martyrLiveData = MutableLiveData<Data>()

    init {
        martyrLiveData.value = bundle.getParcelable(EXTRA_KEY_DATA)
    }

}