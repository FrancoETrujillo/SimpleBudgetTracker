package com.mvatech.ftrujillo.simplebudgeting.new_transaction.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mvatech.ftrujillo.simplebudgeting.data.domain.Category
import com.mvatech.ftrujillo.simplebudgeting.data.domain.SpendingGoal
import com.mvatech.ftrujillo.simplebudgeting.data.domain.Transaction
import com.mvatech.ftrujillo.simplebudgeting.data.repository.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.threeten.bp.LocalDateTime
import java.math.BigDecimal

class NewTransactionViewModel(private val repository: Repository) : ViewModel() {

    val categoryList: LiveData<List<Category>> get() = repository.getCategoryList()


    val currentGoalInformation: LiveData<SpendingGoal> get() {
       return repository.currentGoalInformation
    }

    fun saveNewTransaction(cost:BigDecimal, category: Category, date:LocalDateTime, title:String){
        val transaction = Transaction(cost, date, title, category.color)
        GlobalScope.launch(Dispatchers.IO) {
            repository.saveNewTransaction(transaction)
        }
    }

}
