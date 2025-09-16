package com.ancraz.mywallet_mult.core.di

import com.ancraz.mywallet_mult.data.currency.repository.CurrencyRatesRepositoryImpl
import com.ancraz.mywallet_mult.data.transaction.repository.TransactionRepositoryImpl
import com.ancraz.mywallet_mult.data.wallet.repository.WalletRepositoryImpl
import com.ancraz.mywallet_mult.domain.currency.CurrencyRatesRepository
import com.ancraz.mywallet_mult.domain.transaction.repository.TransactionRepository
import com.ancraz.mywallet_mult.domain.wallet.repository.WalletRepository
import org.koin.core.context.startKoin
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.bind
import org.koin.dsl.module


fun initKoin(config: KoinAppDeclaration? = null) =
    startKoin {
        config?.invoke(this)
        modules(
            provideRepositoryModule
        )
    }


private val provideRepositoryModule = module {
    singleOf(::WalletRepositoryImpl).bind(WalletRepository::class)
    singleOf(::TransactionRepositoryImpl).bind(TransactionRepository::class)
    singleOf(::CurrencyRatesRepositoryImpl).bind(CurrencyRatesRepository::class)
}
