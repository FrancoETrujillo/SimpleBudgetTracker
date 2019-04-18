package com.mvatech.ftrujillo.simplebudgeting.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.mvatech.ftrujillo.simplebudgeting.data.domain.Category
import com.mvatech.ftrujillo.simplebudgeting.data.domain.Transaction

@Dao
interface TransactionDao{
    @Insert
    fun insertTransaction(transaction: Transaction)

    @Delete()
    fun deleteTransaction(transaction: Transaction)

    @Query("DELETE FROM transaction_table")
    fun clearTable()

    @Query("SELECT * FROM transaction_table")
    fun getTransactionList(): LiveData<List<Transaction>>

    @Query("SELECT * FROM transaction_table WHERE id = :id")
    fun getTransactionById(id: Long):LiveData<Transaction>


}