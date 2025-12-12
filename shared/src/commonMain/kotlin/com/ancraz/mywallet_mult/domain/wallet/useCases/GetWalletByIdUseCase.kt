package com.ancraz.mywallet_mult.domain.wallet.useCases

import com.ancraz.mywallet_mult.core.utils.debug.debugLog
import com.ancraz.mywallet_mult.data.dataResult.DataResult
import com.ancraz.mywallet_mult.domain.models.wallet.Wallet
import com.ancraz.mywallet_mult.domain.wallet.repository.WalletRepository

//todo fix this useCase with currencyConverter
class GetWalletByIdUseCase(
    private val walletRepository: WalletRepository,
    //private val dataStoreRepository: DataStoreRepository
) {

    //private val currencyConverter = CurrencyConverter(dataStoreRepository)

    suspend operator fun invoke(id: Long): DataResult<Wallet> {
        return try {
            walletRepository.getWalletById(id).let { wallet ->
                DataResult.Success(
                    wallet.copy(
                        //totalBalance = getTotalBalance(wallet.currencyAccounts)
                    )
                )
            }
        } catch (e: Exception) {
            debugLog("GetWalletByIdUseCase exception: ${e.message}")
            DataResult.Error("${e.message}")
        }
    }

//    private suspend fun getTotalBalance(currencyAccountList: List<Wallet.CurrencyAccount>): Float {
//        var totalBalance = 0f
//
//        currencyAccountList.forEach { account ->
//            totalBalance += currencyConverter.convertToUsd(account.value, account.currencyCode) ?: 0f
//        }
//
//        return totalBalance
//    }
}