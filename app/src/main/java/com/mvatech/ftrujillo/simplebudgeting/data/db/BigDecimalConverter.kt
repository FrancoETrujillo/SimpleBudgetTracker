package com.mvatech.ftrujillo.simplebudgeting.data.db

import androidx.room.TypeConverter
import java.math.BigDecimal

object BigDecimalConverter {
    @TypeConverter
    @JvmStatic
    fun doubleToBigDecimal(number: Double) = number.let {
        BigDecimal(it)
    }

    @TypeConverter
    @JvmStatic
    fun BigDecimalToDouble(number: BigDecimal) = number.toDouble()
}

