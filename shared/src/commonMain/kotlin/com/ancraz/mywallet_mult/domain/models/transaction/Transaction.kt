package com.ancraz.mywallet_mult.domain.models.transaction

import com.ancraz.mywallet_mult.domain.models.wallet.Wallet

data class Transaction(
    val id: Long,
    val value: Float,
    val currencyCode: String,
    val timeInMillis: Long,
    val description: String?,
    val wallet: Wallet?,
    val transactionType: TransactionType,
    val categoryId: Long
)
