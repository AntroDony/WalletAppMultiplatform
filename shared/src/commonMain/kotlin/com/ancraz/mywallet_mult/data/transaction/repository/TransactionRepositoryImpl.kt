package com.ancraz.mywallet_mult.data.transaction.repository

import com.ancraz.mywallet_mult.core.utils.debug.debugLog
import com.ancraz.mywallet_mult.data.database.dao.TransactionDao
import com.ancraz.mywallet_mult.data.database.dao.WalletDao
import com.ancraz.mywallet_mult.data.mapper.TransactionMapper
import com.ancraz.mywallet_mult.data.mapper.WalletMapper
import com.ancraz.mywallet_mult.domain.models.transaction.Transaction
import com.ancraz.mywallet_mult.domain.models.transaction.TransactionType
import com.ancraz.mywallet_mult.domain.models.wallet.Wallet
import com.ancraz.mywallet_mult.domain.transaction.repository.TransactionRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

//todo add dataStoreManager (to update totalBalance)
class TransactionRepositoryImpl(
    private val transactionDao: TransactionDao,
    private val walletDao: WalletDao,
    private val transactionMapper: TransactionMapper,
    private val walletMapper: WalletMapper
) : TransactionRepository {

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
            val insertResult = transactionDao.insertTransaction(
                transaction.toTransactionEntity()
            )

            if (insertResult >= 0) {
                debugLog("transaction insert success")
                transaction.updateWalletBalance(transaction.transactionType)
            }
        }

    }

    override suspend fun deleteTransaction(transaction: Transaction) {
        transactionMapper.apply {
            val deleteResult = transactionDao.deleteTransaction(
                transaction.toTransactionEntity()
            )

            if (deleteResult > 0) {
                debugLog("transaction delete success")
                transaction.revertTransaction()
            }
        }
    }

    private suspend fun getWalletById(walletId: Long?): Wallet? {
        if (walletId == null) {
            return null
        }

        return try {
            val walletEntity = walletDao.getWalletById(walletId)
            walletMapper.run {
                walletEntity.toWallet()
            }
        } catch (e: IllegalStateException) {
            debugLog("exception: ${e.message}")
            null
        } catch (e: Exception) {
            debugLog("exception: ${e.message}")
            null
        }
    }


    private suspend fun Transaction.updateWalletBalance(
        transactionType: TransactionType
    ) {
        if (wallet == null) {
            debugLog("cannot update wallet. Wallet is null")
            return
        }

        if (!wallet.currencyAccounts.map { it.currencyCode }
                .contains(this.currencyCode)
        ) {
            debugLog("cannot update wallet. Currency account with ${this.currencyCode} is not found.")
            return
        }

        val newAccountSet = wallet.currencyAccounts.map { account ->
            if (account.currencyCode == this.currencyCode) {
                when (transactionType) {
                    TransactionType.INCOME -> {
                        account.copy(value = account.value + this.value)
                    }
                    TransactionType.EXPENSE -> {
                        account.copy(value = account.value - this.value)
                    }
                }
            }
            else {
                account
            }
        }
        updateWallet(
            wallet.copy(
                currencyAccounts = newAccountSet.toSet()
            )
        )
    }


    private suspend fun Transaction.revertTransaction() {
        when(this.transactionType){
            TransactionType.INCOME -> {
                this.updateWalletBalance(TransactionType.EXPENSE)
            }
            TransactionType.EXPENSE -> {
                this.updateWalletBalance(TransactionType.INCOME)
            }
        }
    }


    private suspend fun updateWallet(wallet: Wallet) {
        walletMapper.apply {
            walletDao.updateWallet(
                wallet.toWalletEntity()
            )
        }
    }
}