package com.example.martyrs.feature.main

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.martyrs.R
import com.example.martyrs.common.BaseActivity
import com.example.martyrs.data.Data
import com.example.martyrs.data.Martyr
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.ext.android.get
import org.koin.androidx.viewmodel.ext.android.viewModel
import timber.log.Timber

class MainActivity : BaseActivity() {
    val viewModel: MainViewModel by viewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel.progressBarLiveData.observe(this) {
            setProgressIndicator(it)
        }

        mainRv.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        val martyrListAdapter=MartyrListAdapter(get())
        mainRv.adapter = martyrListAdapter

        viewModel.martyrLiveData.observe(this) {
            Timber.i(it.toString())
            martyrListAdapter.martyrs = it.result.data as ArrayList<Data>
        }

    }
}