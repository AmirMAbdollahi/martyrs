package com.example.martyrs.feature.search

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.martyrs.R
import com.example.martyrs.common.BaseFragment
import com.example.martyrs.data.Data
import com.example.martyrs.data.EmptyState
import com.example.martyrs.feature.common.MartyrsViewModel
import com.example.martyrs.feature.main.MartyrListAdapter
import kotlinx.android.synthetic.main.fragment_search.*
import kotlinx.android.synthetic.main.view_empty_state.view.*
import org.koin.android.ext.android.get
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

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

        etSearch.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable?) {
                if (s !== null) {
                    viewModel.searchMartyr(s.toString())
                }
            }

        })

        viewModel.progressBarLiveData.observe(viewLifecycleOwner) {
            setProgressIndicator(it)
        }

        searchRv.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        val martyrListAdapter = MartyrListAdapter(get())
        searchRv.adapter = martyrListAdapter


        viewModel.martyrEmptyStateLiveData.observe(viewLifecycleOwner) {
            showMartyrEmptyState(it)
            if (it.mustShow) {
                searchRv.visibility = View.GONE
            } else {
                searchRv.visibility = View.VISIBLE
            }
        }

        viewModel.martyrLiveData.observe(viewLifecycleOwner) {
            martyrListAdapter.martyrs = it.result.data as ArrayList<Data>
        }


//        backBtn.setOnClickListener {
//
//        }
    }

    private fun showMartyrEmptyState(emptyState: EmptyState) {
        if (emptyState.mustShow) {
            val emptyStateView = showEmptyState(R.layout.view_empty_state)
            emptyStateView?.let { view ->
                view.emptyStateMessageTv.text = getString(emptyState.messageResId)
            }
        } else {
            emptyView?.visibility = View.GONE
        }
    }

    override fun martyrClick(data: Data) {
//        startActivity(Intent(this, MartyrActivity::class.java).apply {
//            putExtra(EXTRA_KEY_DATA, data)
//        })
    }
}