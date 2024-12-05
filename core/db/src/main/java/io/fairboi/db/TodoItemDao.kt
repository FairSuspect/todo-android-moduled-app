package io.fairboi.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import io.fairboi.domain.model.todo.TodoId
import kotlinx.coroutines.flow.Flow

const val TABLE_NAME = TodoDbItem.TABLE_NAME

@Dao
interface TodoItemDao : TodoLocalDataSource {
    @Query("SELECT * FROM $TABLE_NAME")
    override fun getItemsFlow(): Flow<List<TodoDbItem>>

    @Query("SELECT * FROM $TABLE_NAME")
    override suspend fun getItems(): List<TodoDbItem>

    @Query("DELETE FROM $TABLE_NAME")
    override suspend fun deleteAll()

    @Query("SELECT * FROM $TABLE_NAME WHERE id = :id")
    override suspend fun getItemById(id: TodoId): TodoDbItem?

    @Query("DELETE FROM $TABLE_NAME WHERE id = :id")
    override suspend fun deleteItemById(id: TodoId)

    @Insert
    override suspend fun addItem(item: TodoDbItem)

    @Update
    override suspend fun updateItem(item: TodoDbItem)

    @Insert
    override suspend fun addAll(items: List<TodoDbItem>)

}