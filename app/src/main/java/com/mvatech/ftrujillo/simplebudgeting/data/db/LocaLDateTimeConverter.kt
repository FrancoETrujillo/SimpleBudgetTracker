package com.mvatech.ftrujillo.simplebudgeting.data.db

import androidx.room.TypeConverter
import org.threeten.bp.LocalDateTime
import org.threeten.bp.ZoneOffset

object LocaLDateTimeConverter {
    @TypeConverter
    @JvmStatic
    fun LongToDateTime(time: Long) = time.let {
        LocalDateTime.ofEpochSecond(time,0,ZoneOffset.UTC)
    }

    @TypeConverter
    @JvmStatic
    fun dateTimeToLong(dateTime: LocalDateTime) = dateTime.toEpochSecond(ZoneOffset.UTC)
}

