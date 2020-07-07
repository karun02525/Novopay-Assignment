package com.novopay.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


data class ApiResponse(
    var articles: List<Article>?,
    var status: String?,
    var totalResults: Int?
)

@Parcelize
data class Article(
    var author: String?,
    var content: String?,
    var description: String?,
    var publishedAt: String?,
    var title: String?,
    var url: String?,
    var urlToImage: String?
):Parcelable
