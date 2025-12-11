package com.ancraz.mywallet_mult.data.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

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
    val currencyAccountList: Set<CurrencyAccountDb>,

    @ColumnInfo(name = "wallet_type")
    val walletType: String
){

    @Serializable
    data class CurrencyAccountDb(
        val currencyCode: String,
        val balance: Float
    )
}