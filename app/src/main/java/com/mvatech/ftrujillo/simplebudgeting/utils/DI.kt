package com.mvatech.ftrujillo.simplebudgeting.utils

import com.mvatech.ftrujillo.simplebudgeting.data.repository.Repository
import com.mvatech.ftrujillo.simplebudgeting.data.repository.RepositoryImpl
import com.mvatech.ftrujillo.simplebudgeting.new_transaction.viewmodel.NewTransactionViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    viewModel {NewTransactionViewModel(get()) }
    single<Repository> {RepositoryImpl() }
}
