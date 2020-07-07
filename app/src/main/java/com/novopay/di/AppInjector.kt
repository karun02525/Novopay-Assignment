package com.novopay.di
import com.novopay.mvvm.NewsListViewModel
import com.novopay.mvvm.SourceListViewModel
import com.novopay.network.RestClient
import com.novopay.network.RestClient.webServices
import com.novopay.repository.NewsListRepository
import com.novopay.repository.SourceRepository
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single { NewsListRepository(get()) }
    single { SourceRepository(get()) }

}

val networkModule = module {
    single { RestClient }
    single { webServices() }
}

val viewModelModule = module {
    viewModel {
        SourceListViewModel(get())
    }
    viewModel {
        NewsListViewModel(get())
    }
}