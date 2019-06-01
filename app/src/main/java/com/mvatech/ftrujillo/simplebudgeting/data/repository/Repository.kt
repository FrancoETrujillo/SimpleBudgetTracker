package com.mvatech.ftrujillo.simplebudgeting.data.repository

import androidx.lifecycle.LiveData
import com.mvatech.ftrujillo.simplebudgeting.data.domain.Category
import com.mvatech.ftrujillo.simplebudgeting.data.domain.CategoryStats
import com.mvatech.ftrujillo.simplebudgeting.data.domain.SpendingGoal
import com.mvatech.ftrujillo.simplebudgeting.data.domain.Transaction
import java.math.BigDecimal

interface Repository{
    val categoryStatsList:LiveData<List<CategoryStats>>
    val currentGoalInformation:LiveData<SpendingGoal>
    suspend fun saveNewTransaction(transaction: Transaction)
    suspend fun createNewCategory(category: Category)
    fun getCategoryList(): LiveData<List<Category>>
    fun getCurrentRemainingBlocking():BigDecimal
    fun getCurrentSpent():BigDecimal
    fun getCurrentGoal():BigDecimal
    fun getTransactionsByCategory(categoryId: Int): LiveData<List<Transaction>>
}