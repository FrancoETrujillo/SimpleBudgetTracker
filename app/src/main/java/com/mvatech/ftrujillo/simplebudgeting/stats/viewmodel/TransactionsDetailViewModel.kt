package com.mvatech.ftrujillo.simplebudgeting.stats.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mvatech.ftrujillo.simplebudgeting.data.domain.Transaction
import com.mvatech.ftrujillo.simplebudgeting.data.repository.Repository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class TransactionsDetailViewModel(private val repository: Repository) : ViewModel() {

    fun getTransactionsFromCategory(categoryId: Int): LiveData<List<Transaction>>  = repository.getTransactionsByCategory(categoryId)


}
