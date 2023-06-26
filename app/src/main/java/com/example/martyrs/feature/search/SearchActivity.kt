package com.example.martyrs.feature.search

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.martyrs.R
import kotlinx.android.synthetic.main.activity_search.*

class SearchActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fragmentContainer, SearchFragment())
        }.commit()
    }
}