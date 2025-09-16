package com.ancraz.mywallet_mult.data.transaction.repository

import com.ancraz.mywallet_mult.domain.models.transaction.Transaction
import com.ancraz.mywallet_mult.domain.transaction.repository.TransactionRepository
import kotlinx.coroutines.flow.Flow

class TransactionRepositoryImpl: TransactionRepository {

    override fun getTransactionList(): Flow<List<Transaction>> {
        TODO("Not yet implemented")
    }

    override suspend fun getTransactionById(id: Long): Transaction {
        TODO("Not yet implemented")
    }

    override suspend fun addTransaction(transaction: Transaction) {
        TODO("Not yet implemented")
    }

    override suspend fun updateTransaction(transaction: Transaction) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteTransaction(transaction: Transaction) {
        TODO("Not yet implemented")
    }
}