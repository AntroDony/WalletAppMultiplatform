package com.ancraz.mywallet_mult.data.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "wallets")
data class WalletEntity(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "wallet_id")
    val id: Long = 0L,

    @ColumnInfo(name = "name")
    val name: String,

    @ColumnInfo("description")
    val description: String? = null,

    @ColumnInfo(name = "currency_accounts")
    val currencyAccountList: List<CurrencyAccountDb>,

    @ColumnInfo(name = "wallet_type")
    val walletType: WalletTypeDb
){

    data class CurrencyAccountDb(
        val currencyCode: String,
        val balance: Float
    )


    data class WalletTypeDb(
        val name: String
    )

}