package com.ancraz.mywallet_mult

import com.ancraz.mywallet_mult.core.di.repositoryModule
import org.koin.core.context.startKoin

fun initKoin() {
    startKoin {
        modules(
            repositoryModule
        )
    }
}
