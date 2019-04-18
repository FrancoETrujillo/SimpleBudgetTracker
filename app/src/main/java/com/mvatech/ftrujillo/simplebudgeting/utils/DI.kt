package com.mvatech.ftrujillo.simplebudgeting.utils

import com.mvatech.ftrujillo.simplebudgeting.data.db.SimpleBudgetDatabase
import com.mvatech.ftrujillo.simplebudgeting.data.db.TransactionDao
import com.mvatech.ftrujillo.simplebudgeting.data.repository.Repository
import com.mvatech.ftrujillo.simplebudgeting.data.repository.RepositoryImpl
import com.mvatech.ftrujillo.simplebudgeting.new_transaction.viewmodel.NewTransactionViewModel
import com.mvatech.ftrujillo.simplebudgeting.stats.viewmodel.StatsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {


    viewModel {NewTransactionViewModel(get()) }
    viewModel { StatsViewModel(get()) }
    single { SimpleBudgetDatabase(get())}
    single { get<SimpleBudgetDatabase>().transactionDao }

    single<Repository> {RepositoryImpl(get())}
}
