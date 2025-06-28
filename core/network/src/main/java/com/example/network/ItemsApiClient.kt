package com.example.network

import com.example.network.dto.TodoItemRequestDto
import com.example.network.dto.TodoListResponseDto

interface ItemsApiClient {

    suspend fun getAllItems(): TodoListResponseDto
    suspend fun createItem(item: TodoItemRequestDto): TodoListResponseDto
    suspend fun updateItem(item: TodoItemRequestDto): TodoListResponseDto
    suspend fun deleteItem(id: String): TodoListResponseDto

}