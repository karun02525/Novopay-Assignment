package com.novopay.ui

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.novopay.R
import com.novopay.adapter.NewsListAdapter
import com.novopay.model.Article
import com.novopay.mvvm.NewsListViewModel
import com.novopay.utils.showSnack
import kotlinx.android.synthetic.main.activity_news_list.*
import org.koin.android.viewmodel.ext.android.viewModel


class NewsListActivity : AppCompatActivity() {

    private val _viewModel: NewsListViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news_list)

        val country= intent.getStringExtra("country")?:""
        setupViewModel(country)
        _viewModel.loadCountry(country)

    }

    private fun setupViewModel(country: String) {
        progress_circular.visibility = View.VISIBLE
        _viewModel.getNewsData().observe(this, Observer {
            if (it != null) {
                progress_circular.visibility = View.GONE
                setAdapter(it as MutableList<Article>)
            }
        })
        _viewModel.getErrorMessage().observe(this, Observer {
            progress_circular.visibility = View.GONE
            showSnack(it)
        })
    }

    private fun setAdapter(mutableList: MutableList<Article>) {
        rv_news_list.adapter =
            NewsListAdapter(mutableList)
    }

}
