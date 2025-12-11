package com.ancraz.mywallet_mult.presentation.ui.utils

import com.ancraz.mywallet_mult.core.utils.debug.debugLog
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale


fun Float.toFormattedBalanceString(): String{
    return String.format(Locale.US, "%.2f", this)
}

fun String.toFormattedBalanceString(): String{
    return try {
        if (this.isEmpty()){
            String.format(Locale.US, "%.2f", 0.toFloat())
        }
        String.format(Locale.US, "%.2f", this.toFloat())
    } catch (e: Exception){
        debugLog("toFormattedString exception: ${e.message}")
        this
    }
}

fun Long.timestampToString(): String {
    val dateFormat = SimpleDateFormat("dd MMM, HH:mm", Locale.US)
    val date = Date(this)
    return dateFormat.format(date)
}

fun String.toBalanceFloatValue(): Float {
    return try {
        this.toFloat()
    } catch (e: Exception) {
        debugLog("convert String to Float exception: ${e.message}")
        0f
    }
}
