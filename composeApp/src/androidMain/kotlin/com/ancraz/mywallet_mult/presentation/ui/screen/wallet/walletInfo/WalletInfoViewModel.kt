package com.ancraz.mywallet_mult.presentation.ui.screen.wallet.walletInfo

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ancraz.mywallet_mult.data.dataResult.DataResult
import com.ancraz.mywallet_mult.domain.wallet.useCases.DeleteWalletByIdUseCase
import com.ancraz.mywallet_mult.domain.wallet.useCases.GetWalletByIdUseCase
import com.ancraz.mywallet_mult.domain.wallet.useCases.UpdateWalletUseCase
import com.ancraz.mywallet_mult.presentation.mapper.toWallet
import com.ancraz.mywallet_mult.presentation.mapper.toWalletUi
import com.ancraz.mywallet_mult.presentation.models.WalletUi
import com.ancraz.mywallet_mult.presentation.ui.screen.wallet.WalletUiState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class WalletInfoViewModel(
    private val savedStateHandle: SavedStateHandle,
    private val getWalletByIdUseCase: GetWalletByIdUseCase,
    private val updateWalletUseCase: UpdateWalletUseCase,
    private val deleteWalletUseCase: DeleteWalletByIdUseCase,
): ViewModel() {

    private val ioDispatcher = Dispatchers.IO

    private val _walletUiState = MutableStateFlow(savedStateHandle[WALLET_SAVED_STATE_KEY] ?: WalletUiState())
    val walletUiState = _walletUiState.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000L),
        initialValue = savedStateHandle[WALLET_SAVED_STATE_KEY] ?: WalletUiState()
    )

    fun getWalletById(id: Long){
        viewModelScope.launch(Dispatchers.IO) {
            getWalletByIdUseCase(id).let{ result ->
                when(result){
                    is DataResult.Success -> {
                        _walletUiState.update {
                            it.copy(
                                isLoading = false,
                                wallet = result.data?.toWalletUi()
                            )
                        }
                        savedStateHandle[WALLET_SAVED_STATE_KEY] = _walletUiState.value
                    }
                    is DataResult.Loading -> {
                        _walletUiState.value = _walletUiState.value.copy(
                            isLoading = true
                        )
                    }
                    is DataResult.Error -> {
                        _walletUiState.value = _walletUiState.value.copy(
                            error = result.errorMessage
                        )
                    }
                }
            }
        }
    }

    fun updateWallet(wallet: WalletUi){
        viewModelScope.launch(ioDispatcher) {
            updateWalletUseCase(wallet.toWallet())

            updateWalletSavedStateHandle()
        }
    }

    private fun updateWalletSavedStateHandle(){
        savedStateHandle[WALLET_SAVED_STATE_KEY] = _walletUiState.value
    }

    fun deleteWallet(walletId: Long){
        viewModelScope.launch(ioDispatcher) {
            deleteWalletUseCase(walletId)
        }
    }

    companion object {
        private const val WALLET_SAVED_STATE_KEY = "walletUiState"
    }
}