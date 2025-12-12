package com.ancraz.mywallet_mult.core.di

import com.ancraz.mywallet_mult.data.wallet.repository.WalletRepositoryImpl
import com.ancraz.mywallet_mult.domain.wallet.repository.WalletRepository
import com.ancraz.mywallet_mult.domain.wallet.useCases.AddWalletUseCase
import com.ancraz.mywallet_mult.domain.wallet.useCases.GetAllWalletsUseCase
import com.ancraz.mywallet_mult.presentation.ui.screen.home.HomeViewModel
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.bind
import org.koin.dsl.module

val homeScreenModule = module {

    viewModel { HomeViewModel(get(), get()) }
    //singleOf(::WalletRepositoryImpl).bind(WalletRepository::class)
    //factoryOf(::GetAllWalletsUseCase)
    //factoryOf(::AddWalletUseCase)
}
