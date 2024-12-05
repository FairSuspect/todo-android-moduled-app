package io.fairboi.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(
    entities = [TodoDbItem::class],
    version = 1,
    exportSchema = true,
)
@TypeConverters(TimeConverters::class)
internal abstract class TodoDatabase : RoomDatabase() {
    abstract fun getTodoItemDao(): TodoItemDao
}