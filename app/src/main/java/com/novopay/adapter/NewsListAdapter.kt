package com.novopay.adapter

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityOptionsCompat
import androidx.core.util.Pair
import androidx.recyclerview.widget.RecyclerView
import com.novopay.R
import com.novopay.model.Article
import com.novopay.ui.DetailsActivity
import com.novopay.utils.dateFormat
import com.novopay.utils.dateToTimeFormat
import com.novopay.utils.loadImage
import kotlinx.android.synthetic.main.adapter_news.view.*

class NewsListAdapter (var list: List<Article>) : RecyclerView.Adapter<NewsListAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context)
            .inflate(R.layout.adapter_news, parent, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItems(list[position])
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val context: Context =itemView.context
        fun bindItems(model: Article) {
            itemView.apply {
                img.loadImage(model.urlToImage?:"")
                author.text = model.author?:"N.A"
                publishedAt.text = model.publishedAt?.dateFormat()
                title.text = model.title?:"N.A"
                desc.text = model.description?:"N.A"
                time.text = "\u2022 " + model.publishedAt?.dateToTimeFormat()
            }.setOnClickListener {
                val sharedIntent = Intent(context, DetailsActivity::class.java)
                    .putExtra("article", model)
                val pair1 = Pair.create(itemView.img as View, "iv_news")
                val pair2 = Pair.create(itemView.title as View, "tv_title")
                val pair3 = Pair.create(itemView.desc as View, "tv_desc")
                val options = ActivityOptionsCompat.makeSceneTransitionAnimation(context as Activity, pair1, pair2,pair3)
                context.startActivity(sharedIntent, options.toBundle())

            }
        }
    }

}