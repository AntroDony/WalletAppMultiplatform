package com.ancraz.mywallet_mult.presentation.mapper

import com.ancraz.mywallet_mult.domain.models.wallet.Wallet
import com.ancraz.mywallet_mult.presentation.models.WalletUi
import com.ancraz.mywallet_mult.presentation.ui.utils.toBalanceFloatValue
import com.ancraz.mywallet_mult.presentation.ui.utils.toFormattedBalanceString

fun Wallet.toWalletUi(): WalletUi {
    return WalletUi(
        id = this.id,
        name = this.name,
        description = this.description,
        accounts = this.currencyAccounts.map { account -> account.toAccountUi()}.toSet(),
        walletType = this.walletType,
        totalBalance = "${this.getTotalBalance().toFormattedBalanceString()} USD"
    )
}

fun WalletUi.toWallet(): Wallet{
    //val totalBalance = this.accounts.map { it.moneyValue.toBalanceFloatValue() }.sum()

    return Wallet(
        id = this.id,
        name = this.name,
        description = this.description,
        currencyAccounts = this.accounts.map { account -> account.toCurrencyAccount()}.toSet(),
        walletType = this.walletType,
        //totalBalance = totalBalance
    )
}


fun Wallet.CurrencyAccount.toAccountUi(): WalletUi.CurrencyAccountUi {
    return WalletUi.CurrencyAccountUi(
        currency = this.currencyCode,
        moneyValue = this.value.toFormattedBalanceString()
    )
}


fun WalletUi.CurrencyAccountUi.toCurrencyAccount(): Wallet.CurrencyAccount {
    return Wallet.CurrencyAccount(
        currencyCode = this.currency,
        value = this.moneyValue.toBalanceFloatValue()
    )
}

private fun Wallet.getTotalBalance(): Float {
    return 0f//this.currencyAccounts.map { it.value.toBalanceFloatValue() }.sum()
}