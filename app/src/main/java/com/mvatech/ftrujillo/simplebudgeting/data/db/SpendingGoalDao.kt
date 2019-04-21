package com.mvatech.ftrujillo.simplebudgeting.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mvatech.ftrujillo.simplebudgeting.data.domain.SpendingGoal
import java.math.BigDecimal

@Dao
interface SpendingGoalDao{

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertSpendingGoal(spendingGoal: SpendingGoal)

    @Query("SELECT * FROM goal_history_table")
    fun getSpendingGoalHistory(): LiveData<List<SpendingGoal>>

    @Query("SELECT * FROM goal_history_table WHERE id = :id")
    fun getSpendingGoalById(id: String): SpendingGoal?

    @Query("SELECT * FROM goal_history_table ORDER BY periodStart ASC LIMIT 1 ")
    fun getCurrentGoalInfo():LiveData<SpendingGoal>

    @Query("SELECT currentSpent FROM goal_history_table ORDER BY periodStart ASC LIMIT 1")
    fun getCurrentSpent():BigDecimal
}