package com.mvatech.ftrujillo.simplebudgeting.utils

import androidx.room.TypeConverter
import java.math.BigDecimal

object BigDecimalConverter {
    @TypeConverter
    @JvmStatic
    fun doubleToBigDecimal(number: Double): BigDecimal = number.let {
        BigDecimal(it).setScale(2, BigDecimal.ROUND_FLOOR)
    }

    @TypeConverter
    @JvmStatic
    fun bigDecimalToDouble(number: BigDecimal) = number.toDouble()
}

