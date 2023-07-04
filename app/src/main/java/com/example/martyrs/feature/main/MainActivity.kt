package com.example.martyrs.feature.main

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.martyrs.R
import com.example.martyrs.common.BaseActivity
import com.example.martyrs.common.EXTRA_KEY_DATA
import com.example.martyrs.data.Data
import com.example.martyrs.feature.Martyr.MartyrActivity
import com.example.martyrs.feature.common.MartyrsViewModel
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.ext.android.get
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf
import timber.log.Timber

class MainActivity : BaseActivity(), MartyrListAdapter.MartyrOnClickListener {
    val viewModel: MartyrsViewModel by viewModel { parametersOf(0) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

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


        val handler = Handler()
        val searchRunnable = Runnable {
            viewModel.getOrSearchMartyr(etSearch.text.toString())
        }

        etSearch.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable?) {
                handler.removeCallbacks(searchRunnable)
                handler.postDelayed(searchRunnable, 500)
            }

        })

        viewModel.martyrEmptyStateLiveData.observe(this) {
            // TODO: back here (getString)
            if (it.mustShow) {
                showEmptyState(it.mustShow, getString(it.messageResId))
                mainRv.visibility = View.GONE
            } else {
                showEmptyState(it.mustShow)
                mainRv.visibility = View.VISIBLE
            }
        }

        viewModel.selectedSortTitleLiveData.observe(this) {
            selectedSortTitleTv.text = getString(it)
        }

//        fabSearch.setOnClickListener {
//            startActivity(Intent(this, SearchActivity::class.java))
//        }

        sortMartyr.setOnClickListener {
            val dialog = MaterialAlertDialogBuilder(this)
                .setSingleChoiceItems(R.array.sortTitleArray, viewModel.sort)
                { dialog, selectedSortIndex ->
                    dialog.dismiss()
                    viewModel.onSelectedSortChangeByUser(selectedSortIndex,etSearch.text.toString())
                }
                .setTitle(getString(R.string.sort))
            dialog.show()
        }

    }

    override fun martyrClick(data: Data) {
        startActivity(Intent(this, MartyrActivity::class.java).apply {
            putExtra(EXTRA_KEY_DATA, data)
        })
    }
}