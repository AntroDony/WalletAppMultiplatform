package com.ancraz.mywallet_mult.domain.wallet.useCases

import com.ancraz.mywallet_mult.domain.models.wallet.Wallet
import com.ancraz.mywallet_mult.domain.wallet.repository.WalletRepository

class GetAllWalletsUseCase(
    private val walletRepository: WalletRepository
) {

    suspend operator fun invoke(): List<Wallet>{
        return walletRepository.getAllWallets()
    }
}