package com.ancraz.mywallet_mult.domain.models.transaction

import kotlinx.serialization.Serializable

@Serializable
enum class TransactionType {
    INCOME,
    EXPENSE
}