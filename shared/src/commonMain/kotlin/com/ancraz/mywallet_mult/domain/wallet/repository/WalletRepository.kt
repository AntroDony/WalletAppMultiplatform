package com.ancraz.mywallet_mult.domain.wallet.repository

import com.ancraz.mywallet_mult.domain.models.wallet.Wallet

interface WalletRepository {

    suspend fun getWalletById(id: Long): Wallet

    suspend fun getAllWallets(): List<Wallet>

    suspend fun addWallet(wallet: Wallet)

    suspend fun updateWallet(wallet: Wallet)

    suspend fun deleteWalletById(id: Long)
}