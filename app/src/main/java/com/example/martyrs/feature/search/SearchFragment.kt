package com.example.martyrs.feature.search

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.martyrs.R
import com.example.martyrs.common.BaseFragment
import com.example.martyrs.common.EXTRA_KEY_DATA
import com.example.martyrs.data.Data
import com.example.martyrs.feature.Martyr.MartyrActivity
import com.example.martyrs.feature.common.MartyrsViewModel
import com.example.martyrs.feature.main.MartyrListAdapter
import kotlinx.android.synthetic.main.fragment_search.*
import org.koin.android.ext.android.get
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf
import timber.log.Timber

@Suppress("DEPRECATION")
class SearchFragment : BaseFragment(), MartyrListAdapter.MartyrOnClickListener {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_search, container, false)
    }

    val viewModel: MartyrsViewModel by viewModel { parametersOf("search") }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val handler = Handler()
        val searchRunnable = Runnable {
            viewModel.searchMartyr(etSearch.text.toString())
        }

        etSearch.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable?) {
                handler.removeCallbacks(searchRunnable)
//                if (s !== null) {
//                    viewModel.searchMartyr(s.toString())
//                }
                handler.postDelayed(searchRunnable, 1000)
            }

        })

        viewModel.progressBarLiveData.observe(viewLifecycleOwner) {
            setProgressIndicator(it)
        }

        searchRv.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        val martyrListAdapter = MartyrListAdapter(get())
        searchRv.adapter = martyrListAdapter

        martyrListAdapter.martyrOnClickListener = this

        viewModel.martyrEmptyStateLiveData.observe(viewLifecycleOwner) {
            // TODO: back here (getString)
            if (it.mustShow) {
                showEmptyState(it.mustShow, getString(it.messageResId))
                searchRv.visibility = View.GONE
            } else {
                showEmptyState(it.mustShow)
                searchRv.visibility = View.VISIBLE
            }
        }

        viewModel.martyrLiveData.observe(viewLifecycleOwner) {
            martyrListAdapter.martyrs = it.result.data as ArrayList<Data>
        }

        backBtn.setOnClickListener {
            activity?.onBackPressed()
        }
    }


    override fun martyrClick(data: Data) {
        Timber.i("sdfa")
        startActivity(Intent(activity, MartyrActivity::class.java).apply {
            putExtra(EXTRA_KEY_DATA, data)
        })
    }
}