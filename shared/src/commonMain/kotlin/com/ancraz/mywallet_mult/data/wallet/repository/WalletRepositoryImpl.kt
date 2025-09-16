package com.ancraz.mywallet_mult.data.wallet.repository

import com.ancraz.mywallet_mult.domain.models.wallet.Wallet
import com.ancraz.mywallet_mult.domain.wallet.repository.WalletRepository

class WalletRepositoryImpl: WalletRepository {

    override suspend fun getWalletById(id: Long): Wallet {
        TODO("Not yet implemented")
    }

    override suspend fun getAllWallets(): List<Wallet> {
        TODO("Not yet implemented")
    }

    override suspend fun addWallet(wallet: Wallet) {
        TODO("Not yet implemented")
    }

    override suspend fun updateWallet(wallet: Wallet) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteWalletById(id: Long) {
        TODO("Not yet implemented")
    }


}