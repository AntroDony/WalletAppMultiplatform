package com.ancraz.mywallet_mult.presentation.navigation

import androidx.navigation3.runtime.NavKey
import com.ancraz.mywallet_mult.domain.models.transaction.TransactionType
import kotlinx.serialization.Serializable

sealed class NavigationRoute: NavKey {

    @Serializable
    object HomeScreen: NavigationRoute()

    @Serializable
    data class TransactionInputScreen(val transactionType: TransactionType): NavigationRoute()

    @Serializable
    object WalletListScreen: NavigationRoute()

    @Serializable
    object TransactionListScreen: NavigationRoute()

    @Serializable
    object AnalyticsScreen: NavigationRoute()

    @Serializable
    object CreateWalletScreen: NavigationRoute()

    @Serializable
    data class EditWalletScreen(val walletId: Long): NavigationRoute()

    @Serializable
    data class WalletInfoScreen(val walletId: Long): NavigationRoute()

    @Serializable
    data class TransactionInfoScreen(val transactionId: Long): NavigationRoute()
}