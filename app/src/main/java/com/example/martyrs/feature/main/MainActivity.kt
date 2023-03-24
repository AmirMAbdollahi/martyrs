package com.example.martyrs.feature.main

import android.os.Bundle
import com.example.martyrs.R
import com.example.martyrs.common.BaseActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : BaseActivity() {
    val viewModel:MainViewModel by viewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setProgressIndicator(true)

    }
}