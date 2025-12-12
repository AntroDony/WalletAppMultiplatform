package com.ancraz.mywallet_mult.data.database.converters

import androidx.room.TypeConverter
import com.ancraz.mywallet_mult.data.database.entity.WalletEntity
import kotlinx.serialization.json.Json

class CurrencyAccountConverter {

    private val json = Json

    @TypeConverter
    fun currencyAccountSetToString(account: Set<WalletEntity.CurrencyAccountDb>): String {
        return json.encodeToString(account)
    }


    @TypeConverter
    fun stringToCurrencyAccountSet(accountString: String): Set<WalletEntity.CurrencyAccountDb>{
        return json.decodeFromString(accountString)
    }
}