package com.ancraz.mywallet_mult.domain.wallet.useCases

import com.ancraz.mywallet_mult.domain.wallet.repository.WalletRepository

class DeleteWalletByIdUseCase(
    private val walletRepository: WalletRepository
) {

    suspend operator fun invoke(id: Long){
        walletRepository.deleteWalletById(id)
    }
}