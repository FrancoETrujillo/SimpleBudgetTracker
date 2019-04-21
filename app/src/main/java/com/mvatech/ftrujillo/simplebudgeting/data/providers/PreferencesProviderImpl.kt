package com.mvatech.ftrujillo.simplebudgeting.data.providers

import android.content.Context
import android.preference.PreferenceManager
import timber.log.Timber
import java.math.BigDecimal

const val SPENDING_GOAL = "SPENDING_GOAL"
const val CURRENT_SPENT = "CURRENT_SPENT"
const val CURRENT_REMAINING = "CURRENT_REMAINING"

class PreferencesProviderImpl(context: Context) : PreferencesProvider {

    private val  appContext = context.applicationContext

    private val preferences = PreferenceManager.getDefaultSharedPreferences(appContext)

    override fun getCurrentGoal(): BigDecimal {
        val stringPreference = preferences.getString(SPENDING_GOAL, "1000")?: "1000"
        return BigDecimal(stringPreference.toDouble())
    }

    override fun getCurrentSpent(): BigDecimal {
        return BigDecimal(preferences.getFloat(CURRENT_SPENT, 0f).toDouble())
    }

    override fun updateCurrentSpent(newTransactionCost: BigDecimal) {
        val newCurrentSpent = getCurrentSpent() + newTransactionCost
        var newRemaining =  getCurrentGoal() - newCurrentSpent
        newRemaining = if (newRemaining <= BigDecimal.ZERO) BigDecimal.ZERO else newRemaining
        Timber.d("Franco  new Values to set from cost $newTransactionCost new spent $newCurrentSpent and new remaining $newRemaining")
        preferences.edit().apply {
            putFloat(CURRENT_SPENT, newCurrentSpent.toFloat())
            putFloat(CURRENT_REMAINING,newRemaining.toFloat())
        }.apply()
    }

    override fun getCurrentRemaining(): BigDecimal {
        return BigDecimal(preferences.getFloat(CURRENT_REMAINING, 1000f).toDouble())
    }


}