package com.mvatech.ftrujillo.simplebudgeting.new_transaction.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mvatech.ftrujillo.simplebudgeting.data.domain.Category
import com.mvatech.ftrujillo.simplebudgeting.data.domain.Transaction
import com.mvatech.ftrujillo.simplebudgeting.data.repository.Repository
import com.mvatech.ftrujillo.simplebudgeting.mocks.getMockedCategoryList
import com.mvatech.ftrujillo.simplebudgeting.mocks.getMockedCurrentGoal
import com.mvatech.ftrujillo.simplebudgeting.mocks.getMockedCurrentRemaining
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.threeten.bp.LocalDateTime
import java.math.BigDecimal

class NewTransactionViewModel(private val repository: Repository) : ViewModel() {
    private val _categoryList = MutableLiveData<List<Category>>()
    private val _currentGoal = MutableLiveData<Int>()
    private val _currentRemaining = MutableLiveData<Int>()

    val categoryList: LiveData<List<Category>> get(){
        GlobalScope.launch(Dispatchers.IO) {
            _categoryList.postValue(repository.getCategoryList())
        }
        return _categoryList
    }
    val currentGoal: LiveData<Int> get() {
        GlobalScope.launch(Dispatchers.IO) {
            _currentGoal.postValue(repository.getCurrentGoal())
        }
       return _currentGoal
    }
    val currentRemaining:LiveData<Int> get(){
        GlobalScope.launch(Dispatchers.IO) {
            _currentRemaining.postValue(repository.getCurrentRemaining())
        }
       return _currentRemaining
    }

    fun saveNewTransaction(cost:BigDecimal, category: Category, date:LocalDateTime, title:String){
        val transaction = Transaction(cost, date, title, category)
        GlobalScope.launch(Dispatchers.IO) {
            repository.saveNewTransaction(transaction)
        }
    }

    fun sync(){

    }

}
