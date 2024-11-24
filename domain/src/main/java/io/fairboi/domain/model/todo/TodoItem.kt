package io.fairboi.domain.model.todo

import java.time.Instant
import java.time.LocalDateTime

typealias TodoId = String

data class TodoItem(
    val id :TodoId,
    val text: String,
    val deadline: LocalDateTime?,
    val importance: TodoImportance = TodoImportance.LOW,
    val done: Boolean,
    val createdAt: Instant,
    val changedAt: Instant?,
)
