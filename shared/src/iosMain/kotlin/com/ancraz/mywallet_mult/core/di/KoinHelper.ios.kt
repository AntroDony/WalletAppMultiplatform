package com.ancraz.mywallet_mult.core.di

import androidx.room.RoomDatabase
import com.ancraz.mywallet_mult.data.database.WalletDatabase
import com.ancraz.mywallet_mult.data.database.getDatabaseBuilder
import org.koin.dsl.module

actual fun platformModule() = module {
    single<RoomDatabase.Builder<WalletDatabase>> {
        getDatabaseBuilder()
    }
}