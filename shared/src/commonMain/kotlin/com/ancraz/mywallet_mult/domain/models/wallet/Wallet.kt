package com.ancraz.mywallet_mult.domain.models.wallet

data class Wallet(
    val id: Long,
    val name: String,
    val description: String?,
    val walletType: WalletType,
    val currencyAccounts: Set<CurrencyAccount>
){

    data class CurrencyAccount(
        val currencyCode: String,
        val value: Float
    )
}
