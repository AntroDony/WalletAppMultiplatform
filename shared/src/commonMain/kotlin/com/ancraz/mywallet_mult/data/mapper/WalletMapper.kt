package com.ancraz.mywallet_mult.data.mapper

import com.ancraz.mywallet_mult.data.database.entity.WalletEntity
import com.ancraz.mywallet_mult.domain.models.wallet.Wallet
import com.ancraz.mywallet_mult.domain.models.wallet.WalletType

class WalletMapper {

    fun WalletEntity.toWallet(): Wallet{
        return Wallet(
            id = this.id,
            name = this.name,
            description = this.description ?: "",
            walletType = this.walletType.toWalletType(),
            currencyAccounts = this.currencyAccountList.map {
                it.toCurrencyAccount()
            }
        )
    }


    fun Wallet.toWalletEntity(): WalletEntity{
        return WalletEntity(
            id = this.id,
            name = this.name,
            description = this.description.ifBlank { null },
            walletType = this.walletType.toWalletTypeDb(),
            currencyAccountList = this.currencyAccounts.map {
                it.toCurrencyAccountDb()
            }
        )
    }


    private fun String.toWalletType(): WalletType{
        WalletType.entries.forEach {
            run {
                if (it.name.lowercase() == this.lowercase()){
                    return it
                }
            }
        }
        return WalletType.OTHER
    }


    private fun WalletType.toWalletTypeDb(): String{
        return this.name.lowercase()
    }


    private fun WalletEntity.CurrencyAccountDb.toCurrencyAccount(): Wallet.CurrencyAccount{
        return Wallet.CurrencyAccount(
            currencyCode = this.currencyCode,
            value = this.balance
        )
    }


    private fun Wallet.CurrencyAccount.toCurrencyAccountDb(): WalletEntity.CurrencyAccountDb{
        return WalletEntity.CurrencyAccountDb(
            currencyCode = this.currencyCode,
            balance = this.value
        )
    }
}