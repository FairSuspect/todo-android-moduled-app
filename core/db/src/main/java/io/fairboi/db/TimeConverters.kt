package io.fairboi.db

import androidx.room.TypeConverter
import java.time.Instant
import java.time.LocalDateTime

internal class TimeConverters {
    @TypeConverter
    fun toLocalDateTime(value: Long?): LocalDateTime? {
        return value?.let { LocalDateTime.ofEpochSecond(it, 0, java.time.ZoneOffset.UTC) }
    }
    @TypeConverter
    fun fromLocalDateTime(value: LocalDateTime?): Long? {
        return value?.toEpochSecond(java.time.ZoneOffset.UTC)
    }

    @TypeConverter
    fun toInstant(value: Long?): Instant? {
        return value?.let { Instant.ofEpochSecond(it) }
    }
    @TypeConverter
    fun fromInstant(value: Instant?): Long? {
        return value?.epochSecond
    }
}