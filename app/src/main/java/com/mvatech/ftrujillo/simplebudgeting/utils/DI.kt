package com.mvatech.ftrujillo.simplebudgeting.utils

import com.mvatech.ftrujillo.simplebudgeting.data.db.SimpleBudgetDatabase
import com.mvatech.ftrujillo.simplebudgeting.data.providers.PreferencesProvider
import com.mvatech.ftrujillo.simplebudgeting.data.providers.PreferencesProviderImpl
import com.mvatech.ftrujillo.simplebudgeting.data.repository.Repository
import com.mvatech.ftrujillo.simplebudgeting.data.repository.RepositoryImpl
import com.mvatech.ftrujillo.simplebudgeting.new_transaction.viewmodel.NewTransactionViewModel
import com.mvatech.ftrujillo.simplebudgeting.stats.viewmodel.StatsViewModel
import com.mvatech.ftrujillo.simplebudgeting.stats.viewmodel.TransactionsDetailViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {


    viewModel {NewTransactionViewModel(get()) }
    viewModel { StatsViewModel(get()) }
    viewModel { TransactionsDetailViewModel(get()) }
    single { SimpleBudgetDatabase(get())}
    single { get<SimpleBudgetDatabase>().transactionDao }
    single { get<SimpleBudgetDatabase>().categoriesDao }
    single { get<SimpleBudgetDatabase>().spendingGoalDao }
    single <PreferencesProvider>{PreferencesProviderImpl(get())  }
    single<Repository> {RepositoryImpl(get(), get(), get(), get())}
}
