package com.ancraz.mywallet_mult.core.di

import com.ancraz.mywallet_mult.presentation.ui.screen.home.HomeViewModel
import com.ancraz.mywallet_mult.presentation.ui.screen.wallet.createWallet.CreateWalletViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { HomeViewModel(get(), get()) }
    viewModel { CreateWalletViewModel(get()) }
}
