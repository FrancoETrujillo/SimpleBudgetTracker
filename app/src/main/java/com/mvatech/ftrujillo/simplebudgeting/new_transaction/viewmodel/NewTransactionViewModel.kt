package com.mvatech.ftrujillo.simplebudgeting.new_transaction.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mvatech.ftrujillo.simplebudgeting.data.Category
import com.mvatech.ftrujillo.simplebudgeting.mocks.getMockedCategoryList
import com.mvatech.ftrujillo.simplebudgeting.mocks.getMockedCurrentGoal
import com.mvatech.ftrujillo.simplebudgeting.mocks.getMockedCurrentRemaining
import timber.log.Timber
import java.math.BigDecimal

class NewTransactionViewModel : ViewModel() {
    private val _categoryList = MutableLiveData<List<Category>>()
    private val _currentGoal = MutableLiveData<Int>()
    private val _currentRemaining = MutableLiveData<Int>()

    val categoryList = _categoryList as LiveData<List<Category>>
    val currentGoal = _currentGoal as  LiveData<Int>
    val currentRemaining = _currentRemaining as LiveData<Int>

    fun saveNewTransaction(cost:BigDecimal, category: Category, date:String, title:String){
        Timber.d("Franco Saving $cost - $category - $date - $title")
    }
    fun sync(){
        _categoryList.postValue(getMockedCategoryList())
        _currentGoal.postValue(getMockedCurrentGoal())
        _currentRemaining.postValue((getMockedCurrentRemaining()))
    }
}
