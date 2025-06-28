package com.example.network.dto

import kotlinx.serialization.Serializable

@Serializable
data class TodoItemResponseDto(
    val item: TodoItemDto
)