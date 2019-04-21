package com.mvatech.ftrujillo.simplebudgeting.data.domain

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.Index
import androidx.room.PrimaryKey
import org.threeten.bp.LocalDateTime
import java.lang.StringBuilder
import java.math.BigDecimal

@Entity(tableName = "goal_history_table",indices = [Index( value = ["id"], unique = true)] )
data class SpendingGoal(
    val periodStart: LocalDateTime,
    val currentGoal: BigDecimal,
    val initialGoal:BigDecimal,
    val currentSpent:BigDecimal
){
    @PrimaryKey(autoGenerate = false)
    var id: String = generateKey()

    private fun generateKey()= StringBuilder().apply {
            append(periodStart.year)
            append("_")
            append(periodStart.monthValue)
        }.toString()

    @Ignore
    val currentRemaining = currentGoal - currentSpent
}