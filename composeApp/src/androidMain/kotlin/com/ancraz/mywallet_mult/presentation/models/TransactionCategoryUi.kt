package com.ancraz.mywallet_mult.presentation.models

import android.os.Parcelable
import com.ancraz.mywallet_mult.domain.models.transaction.TransactionType
import kotlinx.android.parcel.Parcelize

@Parcelize
data class TransactionCategoryUi(
    val id: Long = 0L,
    val name: String,
    val iconAssetPath: String,
    val transactionType: TransactionType
): Parcelable