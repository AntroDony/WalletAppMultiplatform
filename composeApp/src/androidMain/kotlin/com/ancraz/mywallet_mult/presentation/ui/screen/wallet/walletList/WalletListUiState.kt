package com.ancraz.mywallet_mult.presentation.ui.screen.wallet.walletList

import android.os.Parcelable
import com.ancraz.mywallet_mult.presentation.models.WalletUi
import kotlinx.parcelize.Parcelize

@Parcelize
data class WalletListUiState(
    val isLoading: Boolean = true,
    val walletList: List<WalletUi> = emptyList(),
    val error: String? = null
): Parcelable