package io.fairboi.db.di.modules

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import io.fairboi.db.TodoDatabase
import io.fairboi.db.TodoDbItem
import io.fairboi.db.TodoItemDao
import io.fairboi.db.di.DatabaseScope

@Module
internal interface RoomModule {
    companion object {
        @DatabaseScope
        @Provides
        fun database(context: Context): TodoDatabase = Room
            .databaseBuilder(context, TodoDatabase::class.java, TodoDbItem.TABLE_NAME)
            .fallbackToDestructiveMigration()
            .build()

        @Provides
        @DatabaseScope
        fun dbDao(db: TodoDatabase): TodoItemDao = db.getTodoItemDao()
    }
}