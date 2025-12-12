package com.ancraz.mywallet_mult.presentation.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CurrencyRateUi(
    val currencyCode: CurrencyCode,
    val rate: Float
): Parcelable