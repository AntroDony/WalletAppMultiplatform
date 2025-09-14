package com.ancraz.mywallet_mult.domain.transaction.useCases

import com.ancraz.mywallet_mult.domain.models.transaction.Transaction
import com.ancraz.mywallet_mult.domain.transaction.repository.TransactionRepository

class GetTransactionByIdUseCase(
    private val transactionRepository: TransactionRepository
) {

    suspend operator fun invoke(id: Long): Transaction{
        return transactionRepository.getTransactionById(id)
    }
}