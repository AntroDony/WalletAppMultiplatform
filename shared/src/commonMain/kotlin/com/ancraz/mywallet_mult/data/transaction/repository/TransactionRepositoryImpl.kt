package com.ancraz.mywallet_mult.data.transaction.repository

import com.ancraz.mywallet_mult.core.utils.debug.debugLog
import com.ancraz.mywallet_mult.data.database.dao.TransactionDao
import com.ancraz.mywallet_mult.data.database.dao.WalletDao
import com.ancraz.mywallet_mult.data.mapper.TransactionMapper
import com.ancraz.mywallet_mult.data.mapper.WalletMapper
import com.ancraz.mywallet_mult.domain.models.transaction.Transaction
import com.ancraz.mywallet_mult.domain.models.wallet.Wallet
import com.ancraz.mywallet_mult.domain.transaction.repository.TransactionRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class TransactionRepositoryImpl(
    private val transactionDao: TransactionDao,
    private val walletDao: WalletDao,
    private val transactionMapper: TransactionMapper,
    private val walletMapper: WalletMapper
): TransactionRepository {

    override fun getTransactionList(): Flow<List<Transaction>> {
        return transactionDao.getAllTransactions().map { transactionList ->
            transactionMapper.run {
                transactionList.map { transaction ->
                    val wallet = getWalletById(transaction.walletId)
                    transaction.toTransaction(wallet)
                }
            }
        }
    }

    override suspend fun getTransactionById(id: Long): Transaction {
        val transaction = transactionDao.getTransactionById(id)
        val wallet = getWalletById(transaction.walletId)
        transactionMapper.apply {
            return transaction.toTransaction(wallet)
        }
    }

    override suspend fun addTransaction(transaction: Transaction) {
        transactionMapper.apply {
            transactionDao.insertTransaction(
                transaction.toTransactionEntity()
            )
        }
    }

    override suspend fun updateTransaction(transaction: Transaction) {
        transactionMapper.apply {
            transactionDao.updateTransaction(
                transaction.toTransactionEntity()
            )
        }
    }

    override suspend fun deleteTransaction(transaction: Transaction) {
        transactionMapper.apply {
            transactionDao.deleteTransaction(
                transaction.toTransactionEntity()
            )
        }
    }

    override suspend fun deleteTransactionById(id: Long) {
        transactionDao.getTransactionById(id)
    }

    private suspend fun getWalletById(walletId: Long?): Wallet?{
        if (walletId == null){
            return null
        }

        return try {
            val walletEntity = walletDao.getWalletById(walletId)
            walletMapper.run {
                walletEntity.toWallet()
            }
        } catch (e: IllegalStateException){
            debugLog("exception: ${e.message}")
            null
        } catch (e: Exception){
            debugLog("exception: ${e.message}")
            null
        }
    }


}