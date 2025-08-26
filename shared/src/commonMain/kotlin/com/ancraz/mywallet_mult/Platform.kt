package com.ancraz.mywallet_mult

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform