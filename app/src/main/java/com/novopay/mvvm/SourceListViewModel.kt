package com.novopay.mvvm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.novopay.model.Sources
import com.novopay.repository.SourceRepository

class SourceListViewModel(private val _repository: SourceRepository) : ViewModel() {

    fun getSourceData(): MutableLiveData<List<Sources>> = _repository.getSource()

    fun getErrorMessage(): MutableLiveData<String> =  _repository.getErrorMessage()

    override fun onCleared() {
        _repository.onCleared()
        super.onCleared()
    }


}
