package com.ancraz.mywallet_mult.domain.wallet.useCases

import com.ancraz.mywallet_mult.domain.models.wallet.Wallet
import com.ancraz.mywallet_mult.domain.wallet.repository.WalletRepository
import kotlinx.coroutines.flow.Flow

class GetAllWalletsUseCase(
    private val walletRepository: WalletRepository
) {

    operator fun invoke(): Flow<List<Wallet>> {
        return walletRepository.getAllWallets()
    }
}