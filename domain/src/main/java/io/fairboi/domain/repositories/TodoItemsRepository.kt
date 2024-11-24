package io.fairboi.domain.repositories

import io.fairboi.domain.model.todo.TodoId
import io.fairboi.domain.model.todo.TodoItem
import kotlinx.coroutines.flow.Flow

interface TodoItemsRepository {
    fun getItemsFlow() : Flow<List<TodoItem>>

    suspend fun getItems(): List<TodoItem>

    suspend fun getItemById(id: TodoId): TodoItem?

    suspend fun addItem(item: TodoItem)

    suspend fun updateItem(item: TodoItem)

    suspend fun deleteItemById(id: TodoId)

    suspend fun deleteAll()

    suspend fun addAll(items: List<TodoItem>)

}