package com.example.martyrs

import android.app.Application
import com.example.martyrs.data.repository.DataSource.MartyrDataSource
import com.example.martyrs.data.repository.DataSource.MartyrRemoteDataSource
import com.example.martyrs.data.repository.MartyrRepository
import com.example.martyrs.data.repository.MartyrRepositoryImpl
import com.example.martyrs.feature.main.MainViewModel
import com.example.martyrs.services.FrescoImageLoadingService
import com.example.martyrs.services.ImageLoadingService
import com.example.martyrs.services.http.ApiService
import com.example.martyrs.services.http.createApiServiceInstance
import com.facebook.drawee.backends.pipeline.Fresco
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

        Fresco.initialize(this)

        val myModules = module {
            single<ApiService> { createApiServiceInstance() }
            single<ImageLoadingService> { FrescoImageLoadingService() }
            factory<MartyrRepository> { MartyrRepositoryImpl(MartyrRemoteDataSource(get())) }
            viewModel { MainViewModel(get()) }
        }
        startKoin {
            androidContext(this@App)
            modules(myModules)
        }
    }

}