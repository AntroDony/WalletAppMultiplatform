package com.ancraz.mywallet_mult.domain.wallet.repository

import com.ancraz.mywallet_mult.domain.models.transaction.TransactionCategory
import com.ancraz.mywallet_mult.domain.models.transaction.TransactionType
import kotlinx.coroutines.flow.Flow

interface TransactionCategoryRepository {

    fun getCategoryList(transactionType: TransactionType): Flow<List<TransactionCategory>>

    suspend fun addTransactionCategory(transactionCategory: TransactionCategory)

    suspend fun updateTransactionCategory(transactionCategory: TransactionCategory)

    suspend fun deleteTransactionCategoryById(id: Long)

}