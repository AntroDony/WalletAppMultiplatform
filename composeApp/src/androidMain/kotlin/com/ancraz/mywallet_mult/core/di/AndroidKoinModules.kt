package com.ancraz.mywallet_mult.core.di

import com.ancraz.mywallet_mult.presentation.ui.screen.home.HomeViewModel
import com.ancraz.mywallet_mult.presentation.ui.screen.transaction.createTransaction.CreateTransactionViewModel
import com.ancraz.mywallet_mult.presentation.ui.screen.wallet.createWallet.CreateWalletViewModel
import com.ancraz.mywallet_mult.presentation.ui.screen.wallet.walletInfo.WalletInfoViewModel
import com.ancraz.mywallet_mult.presentation.ui.screen.wallet.walletList.WalletListViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { HomeViewModel(get(), get()) }
    viewModel { CreateWalletViewModel(get()) }
    viewModel { WalletInfoViewModel(get(), get(), get(), get()) }
    viewModel { WalletListViewModel(get(), get()) }
    viewModel { CreateTransactionViewModel(get()) }
}
