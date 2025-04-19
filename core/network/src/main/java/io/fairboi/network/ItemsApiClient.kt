package io.fairboi.network

import io.fairboi.network.dto.TodoItemDto
import io.fairboi.network.dto.TodoItemRequestDto
import io.fairboi.network.dto.TodoItemResponseDto
import io.fairboi.network.dto.TodoListResponseDto

interface ItemsApiClient {

    suspend fun getAllItems(): TodoListResponseDto
    suspend fun createItem(item: TodoItemRequestDto): TodoItemResponseDto
    suspend fun updateItem(item: TodoItemRequestDto): TodoItemResponseDto
    suspend fun deleteItem(id: String)
    suspend fun refreshAll(items: List<TodoItemDto>): TodoListResponseDto

}