package com.mvatech.ftrujillo.simplebudgeting.data.providers

import android.content.Context
import android.preference.PreferenceManager
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.mvatech.ftrujillo.simplebudgeting.data.domain.Category
import timber.log.Timber
import java.io.IOException
import java.math.BigDecimal
import java.nio.charset.Charset

const val SPENDING_GOAL = "SPENDING_GOAL"
const val CURRENT_SPENT = "CURRENT_SPENT"
const val CURRENT_REMAINING = "CURRENT_REMAINING"
const val FIRST_TIME = "FIRST_TIME"

class PreferencesProviderImpl(context: Context) : PreferencesProvider {

    private val  appContext = context.applicationContext

    private val preferences = PreferenceManager.getDefaultSharedPreferences(appContext)

    override fun getCurrentGoal(): BigDecimal {
        val stringPreference = preferences.getString(SPENDING_GOAL, "1000")?: "1000"
        return BigDecimal(stringPreference.toDouble()).setScale(2, BigDecimal.ROUND_FLOOR)
    }

    override fun getCurrentSpent(): BigDecimal {
        return BigDecimal(preferences.getFloat(CURRENT_SPENT, 0f).toDouble()).setScale(2, BigDecimal.ROUND_FLOOR)
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
        return BigDecimal(preferences.getFloat(CURRENT_REMAINING, 1000f).toDouble()).setScale(2, BigDecimal.ROUND_FLOOR)
    }

    override fun isFirstLaunch(): Boolean {
        return preferences.contains(FIRST_TIME)
    }

    override fun setFirstLaunch() {
        preferences.edit().apply{
            putBoolean(FIRST_TIME, true)
        }.apply()
    }

    override suspend fun getDefaultCategories():List<Category>{
        val list = mutableListOf<Category>()
        val json = readFile("DefaultCategories.json")
        val type = object : TypeToken<List<Category?>>() { }.type
        list.addAll(Gson().fromJson<List<Category>>(json, type))
        Timber.d("Franco size default %s", list.size)
        return list
    }

    private fun readFile(filename: String):String{
        try {
            val input = appContext.assets.open(filename)
            val size = input.available()
            val buffer = ByteArray(size)
            input.read(buffer)
            input.close()
            return String(buffer, Charset.forName("UTF-8"))
        } catch (e:IOException){
            Timber.e(e)
        }
       return ""
    }
}