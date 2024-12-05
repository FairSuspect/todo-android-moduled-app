package io.fairboi.db

import io.fairboi.domain.model.todo.TodoId
import kotlinx.coroutines.flow.Flow

interface TodoLocalDataSource {

    fun getItemsFlow(): Flow<List<TodoDbItem>>

    suspend fun getItems(): List<TodoDbItem>

    suspend fun getItemById(id: TodoId): TodoDbItem?

    suspend fun addItem(item: TodoDbItem)

    suspend fun updateItem(item: TodoDbItem)

    suspend fun deleteItemById(id: TodoId)

    suspend fun deleteAll()

    suspend fun addAll(items: List<TodoDbItem>)

}