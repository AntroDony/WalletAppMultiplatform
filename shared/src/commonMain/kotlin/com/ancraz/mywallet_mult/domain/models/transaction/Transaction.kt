package com.ancraz.mywallet_mult.domain.models.transaction

data class Transaction(
    val id: Long,
    val value: Float,
    val currencyCode: String,
    val timeInMillis: Long,
    val description: String?,
    val transactionType: TransactionType
)
