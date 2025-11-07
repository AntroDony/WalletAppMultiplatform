package com.ancraz.mywallet_mult.presentation.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay

@Composable
fun AppNavigation(
    startDestination: NavigationRoute,
    innerPadding: PaddingValues
) {
    val backStack = rememberNavBackStack(startDestination)

    NavDisplay(
        backStack = backStack,
        onBack = {
            backStack.removeLastOrNull()
        },
        entryProvider = entryProvider {
            entry<NavigationRoute.HomeScreen> {
                HomeScreen(
                    paddingValues = innerPadding,
                    onEvent = { event ->
                        when (event) {
                            is HomeUiEvent.CreateTransaction -> {
                                backStack.add(
                                    NavigationRoute.TransactionInputScreen(event.transactionType)
                                )
                            }

                            is HomeUiEvent.ShowAllWallets -> {
                                backStack.add(
                                    NavigationRoute.WalletListScreen
                                )
                            }

                            is HomeUiEvent.ShowAllTransactions -> {
                                backStack.add(
                                    NavigationRoute.TransactionListScreen
                                )
                            }

                            is HomeUiEvent.ShowAnalytics -> {
                                backStack.add(
                                    NavigationRoute.AnalyticsScreen
                                )
                            }

                            is HomeUiEvent.ShowWalletInfo -> {
                                backStack.add(
                                    NavigationRoute.WalletInfoScreen(event.wallet.id)
                                )
                            }

                            is HomeUiEvent.ShowTransactionInfo -> {
                                backStack.add(
                                    NavigationRoute.TransactionInfoScreen(event.transaction.id)
                                )
                            }

                            is HomeUiEvent.CreateWallet -> {
                                backStack.add(
                                    NavigationRoute.CreateWalletScreen
                                )
                            }
                            else -> Unit
                        }
                    },
                )
            }

//            entry<NavigationRoute.TransactionInputScreen> { key ->
//                CreateTransactionScreen(
//                    transactionType = key.transactionType,
//                    paddingValues = innerPadding,
//                    onEvent = { event: CreateTransactionUiEvent ->
//                        when (event) {
//                            is CreateTransactionUiEvent.CreateWallet -> {
//                                backStack.add(
//                                    NavigationRoute.CreateWalletScreen
//                                )
//                            }
//
//                            is CreateTransactionUiEvent.GoBack -> {
//                                backStack.apply {
//                                    if (this.toList().size == 1) {
//                                        add(
//                                            NavigationRoute.HomeScreen
//                                        )
//                                        remove(key)
//                                    } else {
//                                        removeLastOrNull()
//                                    }
//                                }
//                            }
//                            else -> Unit
//                        }
//                    },
//                )
//            }
//
//            entry<NavigationRoute.WalletListScreen> {
//                WalletListScreen(
//                    paddingValues = innerPadding,
//                    onEvent = { event: WalletListUiEvent ->
//                        when (event) {
//                            is WalletListUiEvent.ShowWalletInfo -> {
//                                backStack.add(
//                                    NavigationRoute.WalletInfoScreen(event.wallet.id)
//                                )
//                            }
//
//                            is WalletListUiEvent.CreateWallet -> {
//                                backStack.add(
//                                    NavigationRoute.CreateWalletScreen
//                                )
//                            }
//
//                            is WalletListUiEvent.GoBack -> {
//                                backStack.removeLastOrNull()
//                            }
//                        }
//                    }
//                )
//            }
//
//            entry<NavigationRoute.CreateWalletScreen> {
//                CreateWalletScreen(
//                    paddingValues = innerPadding,
//                    onBack = {
//                        backStack.removeLastOrNull()
//                    }
//                )
//            }
//
//            entry<NavigationRoute.EditWalletScreen> { key ->
//                EditWalletScreen(
//                    walletId = key.walletId,
//                    paddingValues = innerPadding,
//                    onBack = {
//                        backStack.removeLastOrNull()
//                    }
//                )
//            }
//
//            entry<NavigationRoute.WalletInfoScreen> { key ->
//                WalletInfoScreen(
//                    walletId = key.walletId,
//                    paddingValues = innerPadding,
//                    onEvent = { event: WalletInfoUiEvent ->
//                        when (event) {
//                            is WalletInfoUiEvent.EditWallet -> {
//                                backStack.add(
//                                    NavigationRoute.EditWalletScreen(key.walletId)
//                                )
//                            }
//                            is WalletInfoUiEvent.GoBack -> {
//                                backStack.removeLastOrNull()
//                            }
//                            else -> Unit
//                        }
//                    }
//                )
//            }
//
//            entry<NavigationRoute.TransactionListScreen> {
//                TransactionListScreen(
//                    paddingValues = innerPadding,
//                    onEvent = { event: TransactionListUiEvent ->
//                        when (event) {
//                            is TransactionListUiEvent.ShowTransactionInfo -> {
//                                backStack.add(
//                                    NavigationRoute.TransactionInfoScreen(event.transaction.id)
//                                )
//                            }
//
//                            is TransactionListUiEvent.GoBack -> {
//                                backStack.removeLastOrNull()
//                            }
//                            else -> Unit
//                        }
//                    }
//                )
//            }
//
//            entry<NavigationRoute.TransactionInfoScreen> { key ->
//                TransactionInfoScreen(
//                    transactionId = key.transactionId,
//                    paddingValues = innerPadding,
//                    onBack = {
//                        backStack.removeLastOrNull()
//                    }
//                )
//            }
//
//            entry<NavigationRoute.AnalyticsScreen> {
//                AnalyticsScreen(
//                    paddingValues =innerPadding,
//                    onEvent = { event: AnalyticsUiEvent ->
//                        when (event) {
//                            is AnalyticsUiEvent.ShowTransactionInfo -> {
//                                backStack.add(
//                                    NavigationRoute.TransactionInfoScreen(event.transaction.id)
//                                )
//                            }
//
//                            is AnalyticsUiEvent.GoBack -> {
//                                backStack.removeLastOrNull()
//                            }
//                            else -> Unit
//                        }
//                    }
//                )
//            }
        }
    )
}