package com.example.martyrs.common

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import com.example.martyrs.R
import io.reactivex.disposables.CompositeDisposable

abstract class BaseFragment : Fragment(), BaseView {
    override val rootView: CoordinatorLayout?
        get() = view as CoordinatorLayout
    override val viewContext: Context?
        get() = context
}

abstract class BaseActivity : AppCompatActivity(), BaseView {
    override val rootView: ViewGroup
        get() = (findViewById<View>(android.R.id.content) as ViewGroup).getChildAt(0) as ViewGroup
    override val viewContext: Context?
        get() = this
}

interface BaseView {
    val rootView: ViewGroup?
    val viewContext: Context?
    fun setProgressIndicator(mustShow: Boolean) {
        rootView?.let {
            viewContext?.let { context ->
                var loadingView = it.findViewById<View>(R.id.loadingView)
                if (loadingView == null && mustShow) {
                    loadingView =
                        LayoutInflater.from(context).inflate(R.layout.view_loading, it, false)
                    it.addView(loadingView)
                }
                loadingView?.visibility = if (mustShow) View.VISIBLE else View.GONE
            }
        }
    }
}

abstract class BaseViewModel : ViewModel() {
    val compositeDisposable = CompositeDisposable()
    override fun onCleared() {
        compositeDisposable.clear()
        super.onCleared()
    }
}