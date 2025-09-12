package com.ancraz.mywallet_mult.domain.wallet.useCases

import com.ancraz.mywallet_mult.domain.models.wallet.Wallet
import com.ancraz.mywallet_mult.domain.wallet.repository.WalletRepository

class GetWalletByIdUseCase(
    private val walletRepository: WalletRepository
) {

    suspend operator fun invoke(id: Long): Wallet{
        return walletRepository.getWalletById(id)
    }
}