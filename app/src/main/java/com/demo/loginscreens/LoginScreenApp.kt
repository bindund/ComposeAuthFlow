package com.demo.loginscreens

import android.app.Application
import com.demo.auth.data.di.authDataModule
import com.demo.auth.presentation.di.authViewModelModule
import com.demo.loginscreens.di.appModule
import timber.log.Timber
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import android.os.Build
import com.demo.core.data.di.coreDataModule


class LoginScreenApp: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@LoginScreenApp)
            modules(
                authDataModule,
                authViewModelModule,
                appModule,
                coreDataModule
            )
        }
    }
}