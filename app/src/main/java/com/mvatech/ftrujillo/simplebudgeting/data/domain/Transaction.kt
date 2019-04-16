package com.mvatech.ftrujillo.simplebudgeting.data.domain

import org.threeten.bp.LocalDateTime
import java.math.BigDecimal


data class Transaction(
    val price: BigDecimal,
    val time: LocalDateTime,
    val label: String,
    val category: Category)