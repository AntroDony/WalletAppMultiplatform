package com.ancraz.mywallet_mult.domain.models.currency

data class CurrencyData(
    val lastUpdateTime: Long? = null,
    val rates: List<CurrencyRate> = emptyList()
)
