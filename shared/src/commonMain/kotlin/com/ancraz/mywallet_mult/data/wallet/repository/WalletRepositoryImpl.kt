package com.ancraz.mywallet_mult.data.wallet.repository

import com.ancraz.mywallet_mult.data.database.dao.WalletDao
import com.ancraz.mywallet_mult.data.mapper.WalletMapper
import com.ancraz.mywallet_mult.domain.models.wallet.Wallet
import com.ancraz.mywallet_mult.domain.wallet.repository.WalletRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class WalletRepositoryImpl(
    private val walletDao: WalletDao,
    private val mapper: WalletMapper
) : WalletRepository {

    override suspend fun getWalletById(id: Long): Wallet {
        mapper.apply {
            return walletDao.getWalletById(id).toWallet()
        }
    }

    override fun getAllWallets(): Flow<List<Wallet>> {
        return walletDao.getAllWallets().map { walletList ->
            mapper.run {
                walletList.map {
                    it.toWallet()
                }
            }
        }
    }

    override suspend fun addWallet(wallet: Wallet) {
        mapper.apply {
            walletDao.addWallet(
                wallet.toWalletEntity()
            )
        }
    }

    override suspend fun updateWallet(wallet: Wallet) {
        mapper.apply {
            walletDao.updateWallet(
                wallet.toWalletEntity()
            )
        }
    }

    override suspend fun deleteWalletById(id: Long) {
        walletDao.deleteWalletById(id)
    }


}