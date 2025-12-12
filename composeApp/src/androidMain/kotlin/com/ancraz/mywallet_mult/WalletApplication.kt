package com.ancraz.mywallet_mult

import android.app.Application
import com.ancraz.mywallet_mult.core.di.homeScreenModule
import com.ancraz.mywallet_mult.core.di.initKoin
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger

class WalletApplication: Application() {

    override fun onCreate() {
        super.onCreate()

        initKoin {
            androidLogger()
            androidContext(this@WalletApplication.applicationContext)
            modules(homeScreenModule)
        }
    }
}