package com.novopay

import android.app.Application
import android.content.Context
import com.novopay.di.appModule
import com.novopay.di.networkModule
import com.novopay.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        val context = this
        appContext = applicationContext

        val moduleList=listOf(appModule, networkModule,viewModelModule)
        startKoin {
            modules(moduleList).androidContext(context)
        }
    }

    companion object {
        @get:Synchronized
        var appContext: Context? = null
            private set
    }

}