package com.novopay.ui

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.novopay.R
import com.novopay.model.Article
import com.novopay.utils.dateToTimeFormat
import com.novopay.utils.loadImage
import kotlinx.android.synthetic.main.activity_details.*
import kotlinx.android.synthetic.main.toolbar.*

class DetailsActivity : AppCompatActivity() {

    private lateinit var articleData: Article
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        initView()
        initParse()
    }

    private fun initView() {
        articleData = intent.getParcelableExtra("article") as Article
        btnBack.setOnClickListener {
            onBackPressed()
        }
    }

    @SuppressLint("SetTextI18n")
    private fun initParse() {
        tvHeader.text=articleData.author +  " (" +articleData.publishedAt?.dateToTimeFormat() +")"
        tv_title.text = articleData.title?:"N.A"
        tv_desc.text = articleData.description?:"N.A"
        iv_news.loadImage(articleData.urlToImage?:"N.A")
    }
}
