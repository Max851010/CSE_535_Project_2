package com.example.cse_535_project_2_jet.components

import androidx.room.TypeConverter
import java.time.LocalDateTime
import java.util.Date

class DateConverters {
    @TypeConverter
    fun fromTimestamp(value: String?): LocalDateTime? {
        return value?.let { LocalDateTime.parse(it) }
    }

    @TypeConverter
    fun dateToTimestamp(date: LocalDateTime?): String? {
        return date?.toString()
    }
}