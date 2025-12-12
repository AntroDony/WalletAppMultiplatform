package com.ancraz.mywallet_mult.presentation.ui.screen.wallet.walletInfo

sealed class WalletInfoUiEvent {
    data class EditWallet(val walletId: Long): WalletInfoUiEvent()
    data class DeleteWallet(val walletId: Long): WalletInfoUiEvent()
    data object GoBack: WalletInfoUiEvent()
}