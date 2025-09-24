package com.ancraz.mywallet_mult.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ancraz.mywallet_mult.data.database.entity.TransactionCategoryEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface TransactionCategoryDao {

    @Query("SELECT * FROM categories")
    fun getCategories(): Flow<List<TransactionCategoryEntity>>

//    @Query("SELECT * FROM categories WHERE category_type = :categoryType")
//    fun getAllIncomeCategories(categoryType: TransactionCategoryEntity.CategoryTransactionType = TransactionCategoryEntity.CategoryTransactionType.INCOME): Flow<List<TransactionCategoryEntity>>
//
//    @Query("SELECT * FROM categories WHERE category_type = :categoryType")
//    fun getAllExpenseCategories(categoryType: TransactionCategoryEntity.CategoryTransactionType = TransactionCategoryEntity.CategoryTransactionType.EXPENSE): Flow<List<TransactionCategoryEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCategory(category: TransactionCategoryEntity): Long

    @Query("DELETE FROM categories WHERE category_id = :id")
    fun deleteCategoryById(id: Long)
}