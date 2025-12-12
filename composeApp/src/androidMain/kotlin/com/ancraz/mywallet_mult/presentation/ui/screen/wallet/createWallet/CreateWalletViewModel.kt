package com.ancraz.mywallet_mult.presentation.ui.screen.wallet.createWallet

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ancraz.mywallet_mult.domain.wallet.useCases.AddWalletUseCase
import com.ancraz.mywallet_mult.presentation.mapper.toWallet
import com.ancraz.mywallet_mult.presentation.models.WalletUi
import com.ancraz.mywallet_mult.presentation.ui.screen.wallet.WalletUiState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class CreateWalletViewModel(
    private val addWalletUseCase: AddWalletUseCase
): ViewModel() {

    private val ioDispatcher = Dispatchers.IO

    private val _walletUiState = MutableStateFlow(WalletUiState())

    val walletUiState = _walletUiState.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000L),
        initialValue = WalletUiState()
    )

    fun addWallet(walletUi: WalletUi){
        viewModelScope.launch(ioDispatcher) {
            addWalletUseCase(walletUi.toWallet())
        }
    }
}