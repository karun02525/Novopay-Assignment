package com.novopay.repository

import androidx.lifecycle.MutableLiveData
import com.novopay.App
import com.novopay.R
import com.novopay.model.Sources
import com.novopay.network.ApiStatus.isCheckAPIStatus
import com.novopay.network.Const.api_key
import com.novopay.network.RestClient
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class SourceRepository(private val restClient: RestClient) {

    private val sourceList = MutableLiveData<List<Sources>>()
    private val errorMess = MutableLiveData<String>()
    private var job: Job? = null
    init {
        loadSourcesApi()
    }

    private fun loadSourcesApi() {
        job=CoroutineScope(Dispatchers.IO).launch {
            try {
                restClient.webServices().getSourcesAsync(api_key).await().let {
                    if (it.isSuccessful && it.body()?.status == "ok") {
                        val result = it.body()!!.sources
                        result.let {
                            sourceList.postValue(result)
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


    fun getSource(): MutableLiveData<List<Sources>> = sourceList
    fun getErrorMessage(): MutableLiveData<String> = errorMess


    fun onCleared(){
        job?.cancel()
    }
}

