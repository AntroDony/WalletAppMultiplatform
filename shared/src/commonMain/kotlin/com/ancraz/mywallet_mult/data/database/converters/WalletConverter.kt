package com.ancraz.mywallet_mult.data.database.converters

import androidx.room.TypeConverter
import com.ancraz.mywallet_mult.data.database.entity.WalletEntity
import kotlinx.serialization.json.Json

class WalletConverter {

    private val json = Json

    @TypeConverter
    fun currencyAccountListToString(account: List<WalletEntity.CurrencyAccountDb>): String {
        return json.encodeToString(account)
    }


    @TypeConverter
    fun stringToCurrencyAccountList(accountString: String): List<WalletEntity.CurrencyAccountDb>{
        return json.decodeFromString(accountString)
    }
}