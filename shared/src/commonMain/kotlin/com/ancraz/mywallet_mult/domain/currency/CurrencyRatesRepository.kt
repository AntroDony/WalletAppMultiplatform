package com.ancraz.mywallet_mult.domain.currency

import com.ancraz.mywallet_mult.core.utils.Result
import com.ancraz.mywallet_mult.core.utils.network.NetworkError
import com.ancraz.mywallet_mult.domain.models.currency.CurrencyData

interface CurrencyRatesRepository {

    //TODO: move USD to data package
    suspend fun getCurrencyRates(
        desiredCurrencies: List<String>,
        baseCurrencyCode: String = "USD"
    ): Result<CurrencyData, NetworkError>
}