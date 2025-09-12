package com.ancraz.mywallet_mult.domain.wallet.useCases

import com.ancraz.mywallet_mult.domain.models.wallet.Wallet
import com.ancraz.mywallet_mult.domain.wallet.repository.WalletRepository

class AddWalletUseCase(
    private val walletRepository: WalletRepository
) {

    suspend operator fun invoke(wallet: Wallet){
        walletRepository.addWallet(wallet)
    }

}