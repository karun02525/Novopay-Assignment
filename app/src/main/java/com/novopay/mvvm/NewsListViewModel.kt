package com.novopay.mvvm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.novopay.model.Article
import com.novopay.repository.NewsListRepository

class NewsListViewModel(private val _repository: NewsListRepository) : ViewModel() {


    fun loadCountry(country: String) {
        _repository.loadCountry(country)
    }
    fun getNewsData(): MutableLiveData<List<Article>> = _repository.getNewsList()
    fun getErrorMessage(): MutableLiveData<String> = _repository.getErrorMessage()


    override fun onCleared() {
        _repository.onCleared()
        super.onCleared()
    }



}
