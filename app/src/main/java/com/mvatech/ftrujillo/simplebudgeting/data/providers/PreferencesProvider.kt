package com.mvatech.ftrujillo.simplebudgeting.data.providers

import java.math.BigDecimal

interface PreferencesProvider {
    fun getCurrentGoal():BigDecimal
    fun getCurrentSpent():BigDecimal
    fun updateCurrentSpent(newTransactionCost: BigDecimal)
    fun getCurrentRemaining():BigDecimal
}