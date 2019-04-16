package com.mvatech.ftrujillo.simplebudgeting.data.repository

import com.mvatech.ftrujillo.simplebudgeting.data.domain.Category
import com.mvatech.ftrujillo.simplebudgeting.data.domain.Transaction
import com.mvatech.ftrujillo.simplebudgeting.mocks.getMockedCategoryList
import com.mvatech.ftrujillo.simplebudgeting.mocks.getMockedCurrentGoal
import com.mvatech.ftrujillo.simplebudgeting.mocks.getMockedCurrentRemaining
import timber.log.Timber

class RepositoryImpl : Repository{
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
        Timber.d("Franco Saving $transaction")
    }
}