package com.ancraz.mywallet_mult.presentation.models

import android.os.Parcelable
import com.ancraz.mywallet_mult.domain.models.wallet.WalletType
import kotlinx.android.parcel.Parcelize

@Parcelize
data class WalletUi(
    val id: Long = 0L,
    val name: String,
    val description: String? = null,
    val accounts: List<CurrencyAccountUi>,
    val totalBalance: String,
    val walletType: WalletType
): Parcelable {

    @Parcelize
    data class CurrencyAccountUi(
        val currency: String = "USD",
        val moneyValue: String = "0.00"
    ): Parcelable
}