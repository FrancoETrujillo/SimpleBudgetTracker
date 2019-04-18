package com.mvatech.ftrujillo.simplebudgeting.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.mvatech.ftrujillo.simplebudgeting.data.db.TransactionDao
import com.mvatech.ftrujillo.simplebudgeting.data.domain.Category
import com.mvatech.ftrujillo.simplebudgeting.data.domain.CategoryStats
import com.mvatech.ftrujillo.simplebudgeting.data.domain.Transaction
import com.mvatech.ftrujillo.simplebudgeting.mocks.getMockedCategoryList
import com.mvatech.ftrujillo.simplebudgeting.mocks.getMockedCurrentGoal
import com.mvatech.ftrujillo.simplebudgeting.mocks.getMockedCurrentRemaining
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class RepositoryImpl(private val transactionDao: TransactionDao) : Repository{

    init {
        transactionDao.getTransactionList().observeForever {transactionList ->
            GlobalScope.launch(Dispatchers.IO) {
                val returnStatsList = buildCategoryStats(transactionList)
                _statsList.postValue(returnStatsList)
            }
        }
    }

    private val _statsList = MutableLiveData<List<CategoryStats>>()
    override val categoryStatsList: LiveData<List<CategoryStats>>
        get() = _statsList

    override suspend fun createNewCategory() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override suspend fun getCategoryList(): List<Category> {
        return getMockedCategoryList()
    }

    override suspend fun getCurrentGoal(): Int {
        return getMockedCurrentGoal()
    }

    override suspend fun getCurrentSpent(): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override suspend fun getCurrentRemaining(): Int {
        return getMockedCurrentRemaining()
    }

    override suspend fun saveNewTransaction(transaction:Transaction){
        transactionDao.insertTransaction(transaction)
    }

    private fun buildCategoryStats(transactionList: List<Transaction>): List<CategoryStats>{
        return transactionList.map { transaction ->
            CategoryStats(transaction.price, transaction.category.name, transaction.category.color)
        }
    }
}