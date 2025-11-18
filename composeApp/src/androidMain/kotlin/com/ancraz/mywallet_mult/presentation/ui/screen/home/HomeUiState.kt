package com.ancraz.mywallet_mult.presentation.ui.screen.home

import android.os.Parcelable
import com.ancraz.mywallet_mult.presentation.models.TransactionUi
import com.ancraz.mywallet_mult.presentation.models.WalletUi
import kotlinx.android.parcel.Parcelize

@Parcelize
data class HomeUiState(
    val isLoading: Boolean = true,
    val data: HomeScreenData = HomeScreenData(),
    val error: String? = null
): Parcelable {

    @Parcelize
    data class HomeScreenData(
        val balance: Float = 0f,
        val isPrivateMode: Boolean = false,
        val wallets: List<WalletUi> = emptyList(),
        val transactions: List<TransactionUi> = emptyList()
    ): Parcelable
}