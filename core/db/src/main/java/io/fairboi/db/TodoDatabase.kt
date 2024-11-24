package io.fairboi.db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [TodoDbItem::class],
    version = 1,
    exportSchema = true,
)
internal abstract class TodoDatabase : RoomDatabase() {
    abstract fun getTodoItemDao(): TodoItemDao
}