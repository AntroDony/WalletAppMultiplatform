package com.ancraz.mywallet_mult.domain.transaction.useCases

import com.ancraz.mywallet_mult.domain.models.transaction.Transaction
import com.ancraz.mywallet_mult.domain.transaction.repository.TransactionRepository
import kotlinx.coroutines.flow.Flow

class GetTransactionListUseCase(
    private val transactionRepository: TransactionRepository
) {

    operator fun invoke(): Flow<List<Transaction>>{
        return transactionRepository.getTransactionList()
    }
}