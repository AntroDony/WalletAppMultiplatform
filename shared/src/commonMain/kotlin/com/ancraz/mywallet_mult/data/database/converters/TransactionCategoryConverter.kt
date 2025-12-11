package com.ancraz.mywallet_mult.data.database.converters

import androidx.room.TypeConverter
import com.ancraz.mywallet_mult.data.database.entity.TransactionCategoryEntity
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

class TransactionCategoryConverter {

    private val json = Json

    @TypeConverter
    fun categoryEntityToString(categoryEntity: TransactionCategoryEntity?): String{
        return json.encodeToString(categoryEntity)
    }

    @TypeConverter
    fun stringToCategoryEntity(categoryString: String): TransactionCategoryEntity?{
        return json.decodeFromString(categoryString)
    }
}