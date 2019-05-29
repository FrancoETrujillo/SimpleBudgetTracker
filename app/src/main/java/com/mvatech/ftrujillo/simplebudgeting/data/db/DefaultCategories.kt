package com.mvatech.ftrujillo.simplebudgeting.data.db

import android.graphics.Color
import com.mvatech.ftrujillo.simplebudgeting.data.domain.Category
import com.mvatech.ftrujillo.simplebudgeting.data.domain.SpendingGoal
import org.threeten.bp.LocalDateTime
import org.threeten.bp.ZoneOffset
import timber.log.Timber
import java.math.BigDecimal

val defaultCategoriesDao =   mutableListOf<Category>().apply {
    add(Category(Color.YELLOW, "Food"))
    add(Category(Color.RED, "Drinks"))
    add(Category(Color.GREEN, "Bills"))
}.also {         Timber.d("Franco inside  $it id $it")}

val defaultSpendingGoal:SpendingGoal = run{
    val now = LocalDateTime.ofEpochSecond(1555622303, 0, ZoneOffset.UTC)
    val beginningOfMonth = now.minusDays(now.dayOfMonth.toLong())
    SpendingGoal(beginningOfMonth, BigDecimal("500"), BigDecimal("500"),BigDecimal(350))
}.also {         Timber.d("Franco inside  $it id ${it.id}")}
