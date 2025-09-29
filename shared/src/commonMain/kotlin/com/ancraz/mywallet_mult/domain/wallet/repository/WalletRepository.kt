package com.ancraz.mywallet_mult.domain.wallet.repository

import com.ancraz.mywallet_mult.domain.models.wallet.Wallet
import kotlinx.coroutines.flow.Flow

interface WalletRepository {

    fun getAllWallets(): Flow<List<Wallet>>

    suspend fun getWalletById(id: Long): Wallet

    suspend fun addWallet(wallet: Wallet)

    suspend fun updateWallet(wallet: Wallet)

    suspend fun deleteWalletById(id: Long)
}