package com.ancraz.mywallet_mult.presentation.ui.screen.home

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ancraz.mywallet_mult.core.utils.debug.debugLog
import com.ancraz.mywallet_mult.domain.models.transaction.Transaction
import com.ancraz.mywallet_mult.domain.models.wallet.Wallet
import com.ancraz.mywallet_mult.domain.wallet.useCases.GetAllWalletsUseCase
import com.ancraz.mywallet_mult.presentation.mapper.toWalletUi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class HomeViewModel(
    private val savedStateHandle: SavedStateHandle,
    //private val getAllTransactionsUseCase: GetAllTransactionsUseCase,
    private val getAllWalletsUseCase: GetAllWalletsUseCase,
    //private val updateCurrencyRatesUseCase: UpdateCurrencyRatesUseCase,
    //private val dataStoreManager: DataStoreManager
) : ViewModel() {

    private val ioDispatcher = Dispatchers.IO

    private var _homeUiState =
        MutableStateFlow(savedStateHandle[UI_SAVED_STATE_KEY] ?: HomeUiState())
    val homeUiState = _homeUiState.stateIn(
        viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = savedStateHandle[UI_SAVED_STATE_KEY] ?: HomeUiState()
    )

    private val privateModeStatusFlow = flowOf(false)//dataStoreManager.getPrivateModeStatus()
    private val totalBalanceFlow = flowOf(0f)//dataStoreManager.getTotalBalance()
    private val walletListFlow: Flow<List<Wallet>> = getAllWalletsUseCase()
    //private val transactionListFlow: Flow<List<Transaction>> = getAllTransactionsUseCase()

    init {
        fetchData()
    }

    fun syncData() {
        viewModelScope.launch(ioDispatcher) {
//            getAllWalletsUseCase().collect { walletList ->
//                val walletSumInUsd = walletList.map { wallet ->
//                    wallet.totalBalance
//                }.sum()
//
//                walletSumInUsd.let { value ->
//                    dataStoreManager.editTotalBalance(value, CurrencyCode.USD)
//                }
//            }
        }
    }

    private fun fetchData() {
        syncDataAfterWalletListUpdated()
        fetchCurrencyRatesData()
        viewModelScope.launch(ioDispatcher) {
            try {
                combine(
                    privateModeStatusFlow,
                    totalBalanceFlow,
                    walletListFlow,
                    //transactionListFlow,
                ) { privateModeStatus, totalBalance, walletList -> //,  transactionList ->
                    HomeUiState(
                        isLoading = false,
                        data = HomeUiState.HomeScreenData(
                            isPrivateMode = privateModeStatus,
                            balance = totalBalance,
                            wallets = walletList.map { it.  toWalletUi() },
                            transactions = emptyList() //transactionList.map { it.toTransactionUi() }
                        )
                    )
                }.collect { uiState ->
                    _homeUiState.value = uiState
                    updateSavedStateHandle()

//                    updateWidgetState(
//                        balance = uiState.data.balance,
//                        isPrivateMode = uiState.data.isPrivateMode
//                    )
                }
            } catch (e: Exception) {
                debugLog("fetchHomeData exception: ${e.message}")
                _homeUiState.value = _homeUiState.value.copy(
                    error = e.message
                )
                updateSavedStateHandle()
            }
        }
    }

    private fun fetchCurrencyRatesData(){
//        viewModelScope.launch(ioDispatcher) {
//            updateCurrencyRatesUseCase().collectLatest { currencyRatesResult ->
//                when(currencyRatesResult){
//                    is DataResult.Error<*> -> {
//                        _homeUiState.update {
//                            it.copy(
//                                error = currencyRatesResult.errorMessage
//                            )
//                        }
//                    }
//                    else -> Unit
//                }
//            }
//        }
    }

    private fun updateSavedStateHandle(){
        savedStateHandle[UI_SAVED_STATE_KEY] = _homeUiState.value
    }


    private fun syncDataAfterWalletListUpdated(){
        viewModelScope.launch(Dispatchers.IO) {
            walletListFlow.collect {
                syncData()
            }
        }
    }


    companion object {
        private const val UI_SAVED_STATE_KEY = "homeUiState"
    }
}