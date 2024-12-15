package io.fairboi.domain.model.todo

import java.time.Instant
import java.time.LocalDateTime
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

typealias TodoId = String

data class TodoItem(
    val id :TodoId,
    val text: String,
    val deadline: LocalDateTime? = null,
    val importance: TodoImportance = TodoImportance.LOW,
    val done: Boolean = false,
    val createdAt: Instant,
    val changedAt: Instant? = null,
) {

    companion object{
    @OptIn(ExperimentalUuidApi::class)
    fun fromText(text: String): TodoItem {
        val uuid = Uuid.random().toString()
        val now = Instant.now()
        val todo = TodoItem(
            id = uuid,
            text = text,
            createdAt = now,
        )
        return todo
    }
        fun blank() : TodoItem {
            return fromText("")
        }

    }
}
