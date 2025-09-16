package com.ancraz.mywallet_mult.data.currency.repository

import com.ancraz.mywallet_mult.core.utils.Result
import com.ancraz.mywallet_mult.core.utils.network.NetworkError
import com.ancraz.mywallet_mult.domain.currency.CurrencyRatesRepository
import com.ancraz.mywallet_mult.domain.models.currency.CurrencyData

class CurrencyRatesRepositoryImpl: CurrencyRatesRepository {

    override suspend fun getCurrencyRates(
        desiredCurrencies: List<String>,
        baseCurrencyCode: String
    ): Result<CurrencyData, NetworkError> {
        TODO("Not yet implemented")
    }
}