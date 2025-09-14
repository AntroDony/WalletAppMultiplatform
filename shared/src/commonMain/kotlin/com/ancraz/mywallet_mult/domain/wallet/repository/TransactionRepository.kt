package com.ancraz.mywallet_mult.domain.wallet.repository

import com.ancraz.mywallet_mult.domain.models.transaction.Transaction
import kotlinx.coroutines.flow.Flow

interface TransactionRepository {

    fun getTransactionList(): Flow<List<Transaction>>

    suspend fun getTransactionById(id: Long): Transaction

    suspend fun addTransaction(transaction: Transaction)

    suspend fun updateTransaction(transaction: Transaction)

    suspend fun deleteTransaction(transaction: Transaction)
}