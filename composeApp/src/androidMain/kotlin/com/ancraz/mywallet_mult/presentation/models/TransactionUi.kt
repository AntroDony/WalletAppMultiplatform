package com.ancraz.mywallet_mult.presentation.models

import android.os.Parcelable
import com.ancraz.mywallet_mult.domain.models.transaction.TransactionType
import kotlinx.android.parcel.Parcelize
import java.util.Calendar

@Parcelize
data class TransactionUi(
    val id: Long = 0L,
    val time: Long = Calendar.getInstance().timeInMillis,
    val value: Float,
    val currency: String,
    val type: TransactionType,
    val description: String? = null,
    val category: TransactionCategoryUi? = null,
    val wallet: WalletUi? = null,
    val selectedWalletAccount: WalletUi.CurrencyAccountUi? = null
): Parcelable