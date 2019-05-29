package com.mvatech.ftrujillo.simplebudgeting.data.providers

import com.mvatech.ftrujillo.simplebudgeting.data.domain.Category
import java.math.BigDecimal

interface PreferencesProvider {
    fun getCurrentGoal():BigDecimal
    fun getCurrentSpent():BigDecimal
    fun updateCurrentSpent(newTransactionCost: BigDecimal)
    fun getCurrentRemaining():BigDecimal
    fun isFirstLaunch():Boolean
    suspend fun getDefaultCategories():List<Category>
    fun setFirstLaunch()
}