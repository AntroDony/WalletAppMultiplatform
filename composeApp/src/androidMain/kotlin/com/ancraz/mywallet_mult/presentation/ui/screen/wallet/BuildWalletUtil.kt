package com.ancraz.mywallet_mult.presentation.ui.screen.wallet

import com.ancraz.mywallet_mult.domain.models.wallet.WalletType
import com.ancraz.mywallet_mult.presentation.models.WalletUi

internal fun buildWalletObject(
    id: Long?,
    name: String?,
    description: String?,
    type: WalletType?,
    accounts: Set<WalletUi.CurrencyAccountUi>,
    onError: (String) -> Unit
): WalletUi? {
    if (name.isNullOrEmpty()) {
        onError("Wallet name cannot be empty")
        return null
    }
    if (type == null) {
        onError("Select wallet type")
        return null
    }
    return WalletUi(
        id = id ?: 0,
        name = name,
        description = description,
        walletType = type,
        accounts = accounts,
        totalBalance = ""
    )
}