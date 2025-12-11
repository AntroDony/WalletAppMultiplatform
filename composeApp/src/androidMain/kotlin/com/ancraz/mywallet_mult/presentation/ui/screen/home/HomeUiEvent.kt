package com.ancraz.mywallet_mult.presentation.ui.screen.home

import com.ancraz.mywallet_mult.domain.models.transaction.TransactionType
import com.ancraz.mywallet_mult.presentation.models.TransactionUi
import com.ancraz.mywallet_mult.presentation.models.WalletUi

sealed class HomeUiEvent{
    data class CreateTransaction(val transactionType: TransactionType): HomeUiEvent()

    data class ShowWalletInfo(val wallet: WalletUi): HomeUiEvent()
    data class ShowTransactionInfo(val transaction: TransactionUi): HomeUiEvent()

    data class ChangePrivateMode(val isPrivate: Boolean): HomeUiEvent()

    data object CreateWallet: HomeUiEvent()
    data object SyncData: HomeUiEvent()

    data object ShowAllWallets: HomeUiEvent()
    data object ShowAllTransactions: HomeUiEvent()
    data object ShowAnalytics: HomeUiEvent()
}