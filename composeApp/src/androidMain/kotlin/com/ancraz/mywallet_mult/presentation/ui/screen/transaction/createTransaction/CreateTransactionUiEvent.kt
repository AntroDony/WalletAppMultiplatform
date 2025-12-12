package com.ancraz.mywallet_mult.presentation.ui.screen.transaction.createTransaction

import com.ancraz.mywallet_mult.presentation.models.TransactionUi

sealed class CreateTransactionUiEvent{
    data object CreateWallet: CreateTransactionUiEvent()
    data class AddTransaction(val transaction: TransactionUi): CreateTransactionUiEvent()
    data object GoBack: CreateTransactionUiEvent()
}