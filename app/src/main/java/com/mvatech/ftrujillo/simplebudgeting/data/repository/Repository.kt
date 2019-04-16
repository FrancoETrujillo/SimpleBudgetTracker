package com.mvatech.ftrujillo.simplebudgeting.data.repository

import com.mvatech.ftrujillo.simplebudgeting.data.domain.Category
import com.mvatech.ftrujillo.simplebudgeting.data.domain.Transaction

interface Repository{
    suspend fun saveNewTransaction(transaction: Transaction)
    suspend fun createNewCategory()
    suspend fun getCategoryList():List<Category>
    suspend fun getCurrentGoal():Int
    suspend fun getCurrentSpent():Int
    suspend fun getCurrentRemaining():Int

}