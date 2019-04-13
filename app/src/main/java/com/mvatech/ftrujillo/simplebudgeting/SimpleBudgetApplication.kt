package com.mvatech.ftrujillo.simplebudgeting

import android.app.Application
import com.mvatech.ftrujillo.simplebudgeting.utils.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import timber.log.Timber

class SimpleBudgetApplication:Application(){

    override fun onCreate() {
        super.onCreate()
        if(BuildConfig.DEBUG) Timber.plant(Timber.DebugTree())
        startKoin {
            // declare used Android context
            androidContext(this@SimpleBudgetApplication)
            // declare modules
            modules(appModule)
        }
    }
}