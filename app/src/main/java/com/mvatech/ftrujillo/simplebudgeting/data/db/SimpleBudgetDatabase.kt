package com.mvatech.ftrujillo.simplebudgeting.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
import com.mvatech.ftrujillo.simplebudgeting.data.domain.Category
import com.mvatech.ftrujillo.simplebudgeting.data.domain.SpendingGoal
import com.mvatech.ftrujillo.simplebudgeting.data.domain.Transaction
import com.mvatech.ftrujillo.simplebudgeting.utils.BigDecimalConverter
import com.mvatech.ftrujillo.simplebudgeting.utils.LocaLDateTimeConverter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import timber.log.Timber

const val DATABASE_NAME = "simpleBudget.db"
@Database(entities = [Transaction::class, Category::class, SpendingGoal::class], version = 1, exportSchema = false)
@TypeConverters(LocaLDateTimeConverter::class, BigDecimalConverter::class)
abstract class SimpleBudgetDatabase: RoomDatabase() {

    abstract val transactionDao:TransactionDao
    abstract val categoriesDao:CategoriesDao
    abstract val spendingGoalDao:SpendingGoalDao

    companion object{
        @Volatile
        private var INSTANCE:SimpleBudgetDatabase? = null

        operator fun invoke(context: Context) = INSTANCE?: synchronized(this){
            INSTANCE ?: buildDatabase(context).also { INSTANCE = it }
        }

        private fun buildDatabase(context: Context) = Room.databaseBuilder(context.applicationContext,
            SimpleBudgetDatabase::class.java, DATABASE_NAME).fallbackToDestructiveMigration()
            .addCallback(object : Callback(){
                override fun onCreate(db: SupportSQLiteDatabase) {
                    super.onCreate(db)
                    GlobalScope.launch(Dispatchers.IO) {
                        INSTANCE?.let { populateCategories(it.categoriesDao, it.spendingGoalDao) }
                    }
                }
            })
            .build()

        private fun populateCategories(categoriesDao: CategoriesDao, spendingGoalDao: SpendingGoalDao){
            categoriesDao.insertCategoriesList(defaultCategoriesDao)
            spendingGoalDao.insertSpendingGoal(defaultSpendingGoal)
        }
    }
}