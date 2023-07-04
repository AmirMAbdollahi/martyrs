package com.example.martyrs

import android.app.Application
import android.os.Bundle
import com.example.martyrs.data.repository.CommentRepository
import com.example.martyrs.data.repository.CommentRepositoryImpl
import com.example.martyrs.data.repository.DataSource.CommentRemoteDataSource
import com.example.martyrs.data.repository.DataSource.MartyrRemoteDataSource
import com.example.martyrs.data.repository.MartyrRepository
import com.example.martyrs.data.repository.MartyrRepositoryImpl
import com.example.martyrs.feature.Martyr.comment.AddCommentViewModel
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

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())

        Fresco.initialize(this)

        val myModules = module {
            single<ApiService> { createApiServiceInstance() }
            single<ImageLoadingService> { FrescoImageLoadingService() }
            factory<MartyrRepository> { MartyrRepositoryImpl(MartyrRemoteDataSource(get())) }
            factory<CommentRepository> { CommentRepositoryImpl(CommentRemoteDataSource(get())) }
            viewModel { (sort: Int) ->
                com.example.martyrs.feature.common.MartyrsViewModel(
                    sort, get()
                )
            }
            viewModel { (bundle: Bundle) ->
                com.example.martyrs.feature.Martyr.MartyrViewModel(
                    bundle,
                    get()
                )
            }
            viewModel { AddCommentViewModel(get()) }
        }
        startKoin {
            androidContext(this@App)
            modules(myModules)
        }
    }

}