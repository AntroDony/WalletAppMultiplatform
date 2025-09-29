package com.ancraz.mywallet_mult.core.utils.debug

import android.util.Log
import com.ancraz.mywallet_mult.shared.BuildConfig

actual fun debugLog(message: String) {
    if (BuildConfig.DEBUG){
        val stacktrace = Thread.currentThread().stackTrace[3]
        val methodName = stacktrace.methodName
        val className = stacktrace.className
        Log.e("WalletLog::", "$className.$methodName(): $message")
    }
}