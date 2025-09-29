package com.ancraz.mywallet_mult.data.mapper

import com.ancraz.mywallet_mult.data.database.entity.TransactionEntity
import com.ancraz.mywallet_mult.domain.models.transaction.Transaction
import com.ancraz.mywallet_mult.domain.models.transaction.TransactionType
import com.ancraz.mywallet_mult.domain.models.wallet.Wallet

class TransactionMapper {

    fun Transaction.toTransactionEntity(): TransactionEntity {
        return TransactionEntity(
            id = this.id,
            time = this.timeInMillis,
            value = this.value,
            currencyCode = this.currencyCode,
            description = this.description,
            transactionType = this.transactionType.toTransactionTypeDb(),
            transactionCategoryId = this.categoryId
        )
    }

    fun TransactionEntity.toTransaction(wallet: Wallet?): Transaction{
        return Transaction(
            id = this.id,
            timeInMillis = this.time,
            value = this.value,
            currencyCode = this.currencyCode,
            description = this.description,
            wallet = wallet,
            transactionType = this.transactionType.toTransactionType(),
            categoryId = transactionCategoryId
        )
    }


    private fun TransactionType.toTransactionTypeDb(): TransactionEntity.TransactionTypeDb{
        return if (this == TransactionType.INCOME){
            TransactionEntity.TransactionTypeDb.INCOME
        } else TransactionEntity.TransactionTypeDb.EXPENSE
    }


    private fun TransactionEntity.TransactionTypeDb.toTransactionType(): TransactionType{
        return if (this == TransactionEntity.TransactionTypeDb.INCOME){
            TransactionType.INCOME
        } else TransactionType.EXPENSE
    }
}