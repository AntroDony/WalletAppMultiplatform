package com.ancraz.mywallet_mult.presentation.ui.utils

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBalance
import androidx.compose.material.icons.filled.CreditCard
import androidx.compose.material.icons.filled.CurrencyBitcoin
import androidx.compose.material.icons.filled.Money
import androidx.compose.material.icons.filled.Payments
import androidx.compose.material.icons.filled.WaterfallChart
import androidx.compose.ui.graphics.vector.ImageVector
import com.ancraz.mywallet_mult.domain.models.wallet.WalletType
import com.ancraz.mywallet_mult.presentation.models.WalletUi

internal fun WalletType.icon(): ImageVector {
    return when (this) {
        WalletType.CARD -> Icons.Filled.CreditCard
        WalletType.CASH -> Icons.Filled.Money
        WalletType.BANK_ACCOUNT -> Icons.Filled.AccountBalance
        WalletType.CRYPTO_WALLET -> Icons.Filled.CurrencyBitcoin
        WalletType.INVESTMENTS -> Icons.Filled.WaterfallChart
        WalletType.OTHER -> Icons.Filled.Payments
    }
}

internal fun getTestCurrencyAccountList(): Set<WalletUi.CurrencyAccountUi> {
    return setOf(
        WalletUi.CurrencyAccountUi(currency = "USD", "2000.00"),
        WalletUi.CurrencyAccountUi(currency = "GEL", "567.20"),
        WalletUi.CurrencyAccountUi(currency = "RUB", "2000")
    )
}