package com.ancraz.mywallet_mult.data.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "transactions")
data class TransactionEntity(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "transaction_id")
    val id: Long = 0L,

    @ColumnInfo(name = "time_timestamp")
    val time: Long,

    @ColumnInfo(name = "value")
    val value: Float,

    @ColumnInfo(name = "currency_code")
    val currencyCode: String,

    @ColumnInfo(name = "transaction_type")
    val transactionType: TransactionTypeDb,

    @ColumnInfo(name = "transaction_category")
    val transactionCategoryId: Long,

    @ColumnInfo(name = "description")
    val description: String? = null,

    @ColumnInfo(name = "wallet_id")
    val walletId: Long? = null

){
    enum class TransactionTypeDb {
        INCOME,
        EXPENSE
    }

}