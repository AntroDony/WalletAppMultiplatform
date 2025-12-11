package com.ancraz.mywallet_mult.data.database.converters

import androidx.room.TypeConverter
import com.ancraz.mywallet_mult.domain.models.transaction.TransactionType
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

class TransactionTypeConverter {

    private val json = Json

    @TypeConverter
    fun transactionTypeToString(type: TransactionType): String{
        return json.encodeToString(type)
    }


    @TypeConverter
    fun stringToTransactionType(typeStr: String): TransactionType {
        return json.decodeFromString(typeStr)
    }
}