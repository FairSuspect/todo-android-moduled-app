package com.example.network.dto

import io.fairboi.domain.model.todo.TodoId
import io.fairboi.domain.model.todo.TodoImportance
import io.fairboi.domain.model.todo.TodoItem
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneOffset

@Serializable
data class TodoItemDto(
    val id: TodoId,
    val text: String,
    val importance: String,
    val deadline: Long? = null,
    val done: Boolean,
    @SerialName("created_at") val createdAt: Long,
    @SerialName("changed_at") val changedAt: Long?,
) {
    fun toTodoItem() = TodoItem(
        id = id,
        text = text,
        importance = importance.toImportance(),
        deadline = if (deadline != null) LocalDateTime.ofEpochSecond(
            deadline,
            0,
            ZoneOffset.UTC
        ) else null,
        done = done,
        createdAt = Instant.ofEpochMilli(createdAt),
        changedAt = if (changedAt == null) null else Instant.ofEpochMilli(changedAt),
    )
}

fun TodoItem.toDto() = TodoItemDto(
    id = id,
    text = text,
    importance = importance.toDto(),
    deadline = deadline?.toEpochSecond(ZoneOffset.UTC),
    done = done,
    createdAt = createdAt.toEpochMilli(),
    changedAt = changedAt?.toEpochMilli(),
)


fun TodoImportance.toDto() = when (this) {
    TodoImportance.NO -> "basic"
    TodoImportance.LOW -> "low"
    TodoImportance.HIGH -> "high"
}

fun String.toImportance() = when (this) {
    "basic" -> TodoImportance.NO
    "low" -> TodoImportance.LOW
    "high" -> TodoImportance.HIGH
    else -> throw IllegalArgumentException("Unknown importance: $this")
}


