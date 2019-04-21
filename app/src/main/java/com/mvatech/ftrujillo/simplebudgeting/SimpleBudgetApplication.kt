package com.mvatech.ftrujillo.simplebudgeting

import android.app.Application
import androidx.preference.PreferenceManager
import com.jakewharton.threetenabp.AndroidThreeTen
import com.mvatech.ftrujillo.simplebudgeting.utils.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.logger.AndroidLogger
import org.koin.core.context.startKoin
import timber.log.Timber

class SimpleBudgetApplication:Application(){

    override fun onCreate() {
        super.onCreate()
        if(BuildConfig.DEBUG) Timber.plant(Timber.DebugTree())
        PreferenceManager.setDefaultValues(this, R.xml.preferences, false)

        startKoin {
            AndroidLogger()

            // declare used Android context
            androidContext(this@SimpleBudgetApplication)
            // declare modules
            modules(appModule)
        }
        AndroidThreeTen.init(this)
    }
}