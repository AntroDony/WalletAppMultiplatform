package com.ancraz.mywallet_mult.domain.transaction.useCases

import com.ancraz.mywallet_mult.domain.models.transaction.Transaction
import com.ancraz.mywallet_mult.domain.transaction.repository.TransactionRepository

class DeleteTransactionUseCase(
    private val transactionRepository: TransactionRepository
) {

    suspend operator fun invoke(transaction: Transaction){
        transactionRepository.deleteTransaction(transaction)
    }
}