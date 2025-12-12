package com.ancraz.mywallet_mult.presentation.ui.screen.transaction.createTransaction

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ancraz.mywallet_mult.core.utils.debug.debugLog
import com.ancraz.mywallet_mult.domain.models.currency.CurrencyRate
import com.ancraz.mywallet_mult.domain.models.transaction.Transaction
import com.ancraz.mywallet_mult.domain.models.transaction.TransactionCategory
import com.ancraz.mywallet_mult.domain.models.transaction.TransactionType
import com.ancraz.mywallet_mult.domain.models.wallet.Wallet
import com.ancraz.mywallet_mult.presentation.models.CurrencyCode
import com.ancraz.mywallet_mult.presentation.models.TransactionUi
import com.ancraz.mywallet_mult.presentation.ui.utils.toFormattedBalanceString
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class CreateTransactionViewModel(
    private val getCurrencyRatesUseCase: GetCurrencyRatesUseCase,
    private val transactionManager: TransactionManager,
    private val transactionCategoryManager: TransactionCategoryManager,
    private val walletManager: WalletManager,
    private val dataStoreManager: DataStoreManager
): ViewModel() {

    private val ioDispatcher = Dispatchers.IO

    private var _uiState = MutableStateFlow(CreateTransactionUiState())
    val uiState: StateFlow<CreateTransactionUiState> =
        _uiState.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000L),
            initialValue = CreateTransactionUiState()
        )


    private val totalBalanceFlow: Flow<Float> = dataStoreManager.getTotalBalance()
    private val recentWalletIdFlow: Flow<Long> = dataStoreManager.getRecentWalletId()
    private val recentCurrencyFlow: Flow<CurrencyCode> = dataStoreManager.getRecentCurrency()
    private val incomeTransactionCategoriesFlow: Flow<List<TransactionCategory>> =
        transactionCategoryManager.getCategories(TransactionType.INCOME)
    private val expenseTransactionCategoriesFlow: Flow<List<TransactionCategory>> =
        transactionCategoryManager.getCategories(TransactionType.EXPENSE)
    private val transactionListFlow: Flow<List<Transaction>> = transactionManager.getTransactions()
    private val walletListFlow: Flow<List<Wallet>> = walletManager.getWallets()
    private val currencyRatesDataResultFlow: Flow<List<CurrencyRate>?> = getCurrencyRatesUseCase()

    private var transactionList: List<TransactionUi> = emptyList()

    fun addNewTransaction(transactionUi: TransactionUi) {
        viewModelScope.launch(ioDispatcher) {
            transactionManager.addTransaction(transactionUi.toTransaction())

            //update dataStore values
            dataStoreManager.updateRecentCurrency(transactionUi.currency)
            transactionUi.wallet?.id?.let { id ->
                dataStoreManager.updateRecentWalletId(id)
            }
        }
    }


    fun fetchData() {
        viewModelScope.launch(ioDispatcher) {
            try {
                combine(
                    totalBalanceFlow,
                    recentWalletIdFlow,
                    recentCurrencyFlow,
                    incomeTransactionCategoriesFlow,
                    expenseTransactionCategoriesFlow,
                    transactionListFlow,
                    walletListFlow,
                    currencyRatesDataResultFlow
                ) { values: Array<Any?> ->
                    val totalBalance = values[0] as Float
                    val recentWalletId = values[1] as Long
                    val recentCurrency = values[2] as CurrencyCode
                    val incomeCategories = values[3] as List<TransactionCategory>
                    val expenseCategories = values[4] as List<TransactionCategory>
                    val transactions = values[5] as List<Transaction>
                    val wallets = values[6] as List<Wallet>
                    val currencyRates = values[7] as List<CurrencyRate>?

                    transactionList = transactions.map { it.toTransactionUi() }

                    CreateTransactionUiState(
                        isLoading = false,
                        data = CreateTransactionUiState.TransactionScreenData(
                            totalBalance = totalBalance.toFormattedBalanceString(),
                            incomeCategories = incomeCategories.map { it.toCategoryUi() },
                            expenseCategories = expenseCategories.map { it.toCategoryUi() },
                            currencyRates = currencyRates?.map { it.toCurrencyRateUi() } ?: emptyList(),
                            walletList = wallets.map { it.toWalletUi() },
                            recentWalletId = recentWalletId,
                            recentCurrency = recentCurrency
                        ),
                        error = null
                    )
                }.collect {
                    _uiState.value = it
                }
            } catch (e: Exception) {
                debugLog("fetchCreateTransactionData exception: ${e.message}")

                _uiState.value = _uiState.value.copy(
                    error = e.message
                )
            }
        }
    }
}