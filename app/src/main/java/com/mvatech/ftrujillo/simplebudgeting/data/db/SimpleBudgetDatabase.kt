package com.mvatech.ftrujillo.simplebudgeting.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.mvatech.ftrujillo.simplebudgeting.data.domain.Transaction

const val DATABASE_NAME = "simpleBudget.db"
@Database(entities = [Transaction::class], version = 1, exportSchema = false)
@TypeConverters(LocaLDateTimeConverter::class, BigDecimalConverter::class)
abstract class SimpleBudgetDatabase: RoomDatabase() {

    abstract val transactionDao:TransactionDao

    companion object{
        @Volatile
        private var INSTANCE:SimpleBudgetDatabase? = null

        operator fun invoke(context: Context) = INSTANCE?: synchronized(this){
            INSTANCE ?: buildDatabase(context).also { INSTANCE = it }
        }

        private fun buildDatabase(context: Context) = Room.databaseBuilder(context.applicationContext,
                SimpleBudgetDatabase::class.java, DATABASE_NAME).fallbackToDestructiveMigration()
                .build()

    }
}