package com.example.network.dto

import kotlinx.serialization.Serializable

@Serializable
data class TodoListResponseDto(
    val list: List<TodoItemDto>
)
