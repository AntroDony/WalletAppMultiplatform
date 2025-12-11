package com.ancraz.mywallet_mult.domain.models.wallet

import kotlinx.serialization.Serializable

@Serializable
enum class WalletType(val walletName: String) {
    CASH("Cash"),
    CARD("Card"),
    BANK_ACCOUNT("Bank Account"),
    CRYPTO_WALLET("Crypto Wallet"),
    INVESTMENTS("Investments"),
    OTHER("Other")
}