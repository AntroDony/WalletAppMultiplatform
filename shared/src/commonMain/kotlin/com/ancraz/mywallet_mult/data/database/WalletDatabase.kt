package com.ancraz.mywallet_mult.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.ancraz.mywallet_mult.data.database.converters.TransactionCategoryConverter
import com.ancraz.mywallet_mult.data.database.converters.TransactionTypeConverter
import com.ancraz.mywallet_mult.data.database.converters.WalletConverter
import com.ancraz.mywallet_mult.data.database.dao.TransactionCategoryDao
import com.ancraz.mywallet_mult.data.database.dao.TransactionDao
import com.ancraz.mywallet_mult.data.database.dao.WalletDao
import com.ancraz.mywallet_mult.data.database.entity.TransactionCategoryEntity
import com.ancraz.mywallet_mult.data.database.entity.TransactionEntity
import com.ancraz.mywallet_mult.data.database.entity.WalletEntity

@Database(
    entities = [
        WalletEntity::class,
        TransactionEntity::class,
        TransactionCategoryEntity::class
    ],
    version = 1,
    exportSchema = false
)
@TypeConverters(
    TransactionTypeConverter::class,
    WalletConverter::class,
    TransactionCategoryConverter::class
)
abstract class WalletDatabase : RoomDatabase() {

    abstract fun transactionDao(): TransactionDao

    abstract fun walletDao(): WalletDao

    abstract fun transactionCategoryDao(): TransactionCategoryDao
}