package com.ancraz.mywallet_mult.presentation.ui.screen.wallet.walletList

import com.ancraz.mywallet_mult.presentation.models.WalletUi

sealed class WalletListUiEvent{
    data object CreateWallet: WalletListUiEvent()
    data class ShowWalletInfo(val wallet: WalletUi): WalletListUiEvent()
    data object GoBack: WalletListUiEvent()
}