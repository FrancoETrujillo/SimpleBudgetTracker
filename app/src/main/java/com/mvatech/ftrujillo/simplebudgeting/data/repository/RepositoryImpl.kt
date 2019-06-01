package com.mvatech.ftrujillo.simplebudgeting.data.repository

import android.util.SparseArray
import androidx.core.util.contains
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.mvatech.ftrujillo.simplebudgeting.data.db.CategoriesDao
import com.mvatech.ftrujillo.simplebudgeting.data.db.SpendingGoalDao
import com.mvatech.ftrujillo.simplebudgeting.data.db.TransactionDao
import com.mvatech.ftrujillo.simplebudgeting.data.domain.Category
import com.mvatech.ftrujillo.simplebudgeting.data.domain.CategoryStats
import com.mvatech.ftrujillo.simplebudgeting.data.domain.SpendingGoal
import com.mvatech.ftrujillo.simplebudgeting.data.domain.Transaction
import com.mvatech.ftrujillo.simplebudgeting.data.providers.PreferencesProvider
import com.mvatech.ftrujillo.simplebudgeting.utils.getList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.threeten.bp.LocalDateTime
import timber.log.Timber
import java.math.BigDecimal

class RepositoryImpl(private val transactionDao: TransactionDao,
                     private val categoriesDao: CategoriesDao,
                     private val spendingGoalDao: SpendingGoalDao,
                     private val preferenceProvider: PreferencesProvider) : Repository{
    init {
        transactionDao.getTransactionList().observeForever {transactionList ->
            GlobalScope.launch(Dispatchers.IO) {
                val returnStatsList = buildCategoryStats(transactionList)
                _statsList.postValue(returnStatsList)
            }
        }
    }

    private val _statsList = MutableLiveData<List<CategoryStats>>()

    private val _spendingGoalInfo = MutableLiveData<SpendingGoal>()

    override val currentGoalInformation: LiveData<SpendingGoal> get(){
        GlobalScope.launch(Dispatchers.IO) {
            updateSpendingGoalInfo()
        }
        return _spendingGoalInfo
    }

    private fun updateSpendingGoalInfo() {
        val currentGoal = preferenceProvider.getCurrentGoal()
        val initialGoal= preferenceProvider.getCurrentGoal()
        val currentSpent = preferenceProvider.getCurrentSpent()
        val newGoalInfo = SpendingGoal(LocalDateTime.now(),currentGoal, initialGoal, currentSpent)
        _spendingGoalInfo.postValue(newGoalInfo)
    }

    override fun getCurrentRemainingBlocking():BigDecimal{
        return preferenceProvider.getCurrentRemaining()
    }
    override val categoryStatsList: LiveData<List<CategoryStats>>
        get() = _statsList

    override suspend fun createNewCategory(category: Category) {
        categoriesDao.insertCategory(category)
    }

    override fun getCategoryList(): LiveData<List<Category>> {
        Timber.d("Franco getCategory")

        if(!preferenceProvider.isFirstLaunch()){
            initializeDefaults()
        }
        return categoriesDao.getCategoryList()
    }

    override fun getTransactionsByCategory(categoryId: Int):LiveData<List<Transaction>> {
        return transactionDao.getTransactionByCategory(categoryId)
    }
    override suspend fun saveNewTransaction(transaction:Transaction){
        withContext(Dispatchers.IO){
            preferenceProvider.updateCurrentSpent(transaction.price)
            transactionDao.insertTransaction(transaction)
            updateSpendingGoalInfo()
        }
    }

    override fun getCurrentSpent(): BigDecimal {
        return preferenceProvider.getCurrentSpent()
    }

    override fun getCurrentGoal(): BigDecimal {
        return preferenceProvider.getCurrentGoal()
    }

    private fun initializeDefaults() {
        GlobalScope.launch(Dispatchers.IO) {
            val defaultCategories = preferenceProvider.getDefaultCategories()
            Timber.d("Franco initializing.... %s", defaultCategories)
            categoriesDao.insertCategoriesList(defaultCategories)
            preferenceProvider.setFirstLaunch()
        }
    }

    private fun buildCategoryStats(transactionList: List<Transaction>): List<CategoryStats>{
        val categoryTotalsMap = SparseArray<CategoryStats>()
        transactionList.forEach {transaction ->
            val colorId = transaction.categoryColorId
            val category = categoriesDao.getCategoryById(colorId)

            if(categoryTotalsMap.contains(colorId)){
                val newSpent = categoryTotalsMap[colorId].totalSpent + transaction.price
                val newCategoryStat = CategoryStats(newSpent,category.name, colorId)
                categoryTotalsMap.put(colorId, newCategoryStat)
            }
            else {
                categoryTotalsMap.put(colorId, CategoryStats(
                    transaction.price, category.name, colorId))
            }
        }
        return categoryTotalsMap.getList()
    }

    private fun buildCurrentPeriodId(): String {
        val today = LocalDateTime.now()
        return StringBuilder().apply {
            append(today.year)
            append("_")
            append(today.monthValue)
        }.toString()
    }
}