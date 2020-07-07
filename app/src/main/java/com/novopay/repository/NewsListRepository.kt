package com.novopay.repository

import androidx.lifecycle.MutableLiveData
import com.novopay.App
import com.novopay.R
import com.novopay.model.Article
import com.novopay.network.ApiStatus.isCheckAPIStatus
import com.novopay.network.Const.api_key
import com.novopay.network.RestClient
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class NewsListRepository(private val restClient: RestClient) {

    private val newsList = MutableLiveData<List<Article>>()
    private val errorMess = MutableLiveData<String>()
    private var job: Job? = null

    fun loadCountry(country: String) {
        job=CoroutineScope(Dispatchers.IO).launch {
            try {
                restClient.webServices().getNewsListAsync(country,api_key).await().let {
                    if (it.isSuccessful && it.body()?.status == "ok") {
                        val result = it.body()!!.articles
                        result?.let {
                            newsList.postValue(result)
                        }

                    } else {
                        errorMess.postValue(isCheckAPIStatus(it.code(), it.errorBody()))
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
                errorMess.postValue(App.appContext?.getString(R.string.no_internet_available))
            }
        }
    }

    fun getNewsList(): MutableLiveData<List<Article>> = newsList
    fun getErrorMessage(): MutableLiveData<String> = errorMess

    fun onCleared(){
        job?.cancel()
    }


}

