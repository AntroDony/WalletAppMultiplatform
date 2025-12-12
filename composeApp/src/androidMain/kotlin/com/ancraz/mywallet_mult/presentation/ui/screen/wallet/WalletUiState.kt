package com.ancraz.mywallet_mult.presentation.ui.screen.wallet

import android.os.Parcelable
import androidx.compose.runtime.Stable
import com.ancraz.mywallet_mult.presentation.models.WalletUi
import kotlinx.parcelize.Parcelize

@Stable
@Parcelize
data class WalletUiState(
    val isLoading: Boolean = true,
    val wallet: WalletUi? = null,
    val currencyList: List<String> = emptyList(),
    val error: String? = null
): Parcelable