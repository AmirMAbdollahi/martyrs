package com.example.martyrs.feature.main

import android.content.Intent
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.martyrs.R
import com.example.martyrs.common.BaseActivity
import com.example.martyrs.common.EXTRA_KEY_DATA
import com.example.martyrs.data.Data
import com.example.martyrs.feature.Martyr.MartyrActivity
import com.example.martyrs.feature.common.MartyrsViewModel
import com.example.martyrs.feature.search.SearchActivity
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.ext.android.get
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf
import timber.log.Timber

class MainActivity : BaseActivity(), MartyrListAdapter.MartyrOnClickListener {
    val viewModel: MartyrsViewModel by viewModel { parametersOf("main") }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel.searchMartyr(null)

        viewModel.progressBarLiveData.observe(this) {
            setProgressIndicator(it)
        }

        mainRv.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        val martyrListAdapter = MartyrListAdapter(get())
        mainRv.adapter = martyrListAdapter

        martyrListAdapter.martyrOnClickListener = this

        viewModel.martyrLiveData.observe(this) {
            Timber.i(it.toString())
            martyrListAdapter.martyrs = it!!.result.data as ArrayList<Data>
        }

        fabSearch.setOnClickListener {
            startActivity(Intent(this, SearchActivity::class.java))
        }

    }

    override fun martyrClick(data: Data) {
        startActivity(Intent(this, MartyrActivity::class.java).apply {
            putExtra(EXTRA_KEY_DATA, data)
        })
    }
}