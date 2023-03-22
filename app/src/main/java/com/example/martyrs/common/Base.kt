package com.example.martyrs.common

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable

abstract class BaseFragment : Fragment(), BaseView
abstract class BaseActivity : AppCompatActivity(), BaseView
interface BaseView
abstract class BaseViewModel:ViewModel(){
    val compositeDisposable= CompositeDisposable()
    override fun onCleared() {
        compositeDisposable.clear()
        super.onCleared()
    }
}