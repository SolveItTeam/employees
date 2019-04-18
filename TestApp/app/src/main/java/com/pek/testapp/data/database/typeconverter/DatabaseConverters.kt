package com.pek.testapp.data.database.typeconverter

import androidx.room.TypeConverter
import java.util.*

class DatabaseConverters {

    @TypeConverter
    fun longToDate(time: Long?): Date? = if (time == null) null else Date(time)

    @TypeConverter
    fun dateToLong(date: Date?): Long? = date?.time

}