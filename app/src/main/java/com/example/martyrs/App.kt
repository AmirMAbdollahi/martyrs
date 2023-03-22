package com.example.martyrs

import android.app.Application
import com.example.martyrs.feature.main.MainViewModel
import com.example.martyrs.services.http.ApiService
import com.example.martyrs.services.http.createApiServiceInstance
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module
import timber.log.Timber
import java.util.Timer

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())

        val myModules= module {
            single<ApiService> { createApiServiceInstance() }
            viewModel { MainViewModel() }
        }
        startKoin {
            androidContext(this@App)
            modules(myModules)
        }
    }

}