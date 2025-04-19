package io.fairboi.network.dto

import kotlinx.serialization.Serializable

@Serializable
data class TodoItemRequestDto(
    val item: TodoItemDto,
)
