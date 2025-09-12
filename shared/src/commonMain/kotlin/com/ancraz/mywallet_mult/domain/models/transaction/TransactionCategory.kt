package com.ancraz.mywallet_mult.domain.models.transaction

data class TransactionCategory(
    val id: Long,
    val name: String,
    val categoryType: TransactionType
)