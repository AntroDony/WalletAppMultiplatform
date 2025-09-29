package com.ancraz.mywallet_mult.core.utils.debug

import platform.Foundation.NSLog
import platform.Foundation.NSThread
import kotlin.experimental.ExperimentalNativeApi

@OptIn(ExperimentalNativeApi::class)
actual fun debugLog(message: String) {
    if (Platform.isDebugBinary) {
        val stackSymbols = NSThread.callStackSymbols
        val caller = if (stackSymbols.count() > 2) stackSymbols[2] else "Unknown"
        NSLog("WalletLog:: $caller -> $message")
    }
}