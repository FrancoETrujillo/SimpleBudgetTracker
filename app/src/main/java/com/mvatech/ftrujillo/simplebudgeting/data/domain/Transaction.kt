package com.mvatech.ftrujillo.simplebudgeting.data.domain

import androidx.room.Entity
import androidx.room.PrimaryKey
import org.threeten.bp.LocalDateTime
import java.math.BigDecimal

@Entity(tableName = "transaction_table")
data class Transaction(
    val price: BigDecimal,
    val time: LocalDateTime,
    val label: String,
    val categoryColorId: Int){
    @PrimaryKey(autoGenerate = true)
    var id:Long = 0
}