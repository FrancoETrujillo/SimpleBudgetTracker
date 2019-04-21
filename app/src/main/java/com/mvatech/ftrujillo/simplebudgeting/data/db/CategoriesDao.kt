package com.mvatech.ftrujillo.simplebudgeting.data.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.mvatech.ftrujillo.simplebudgeting.data.domain.Category

@Dao
interface CategoriesDao{
    @Insert(onConflict = OnConflictStrategy.ABORT)
    fun insertCategory(category: Category)

    @Insert
    fun insertCategoriesList(categoryList: List<Category>)

    @Delete
    fun deleteCategory(category: Category)

    @Query("SELECT * FROM categories_table")
    fun getCategoryList(): LiveData<List<Category>>

    @Query("SELECT * FROM categories_table WHERE color = :id")
    fun getCategoryById(id: Int): Category
}