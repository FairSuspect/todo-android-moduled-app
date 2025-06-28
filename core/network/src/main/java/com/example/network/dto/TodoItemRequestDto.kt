package com.example.network.dto

import kotlinx.serialization.Serializable

@Serializable
data class TodoItemRequestDto(
    val element: TodoItemDto,
)
