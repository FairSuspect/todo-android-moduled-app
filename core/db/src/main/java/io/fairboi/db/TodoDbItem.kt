package io.fairboi.db

import androidx.room.ColumnInfo
import androidx.room.PrimaryKey
import io.fairboi.domain.model.todo.TodoId
import io.fairboi.domain.model.todo.TodoImportance
import io.fairboi.domain.model.todo.TodoItem
import java.time.Instant
import java.time.LocalDateTime


data class TodoDbItem
    (
    @PrimaryKey val id: TodoId,
    val text: String,
    val deadline: LocalDateTime?,
    val importance: TodoImportance = TodoImportance.LOW,
    val done: Boolean,
    @ColumnInfo("created_at")
    val createdAt: Instant,
    @ColumnInfo("changed_at")
    val changedAt: Instant?,
) {
    fun toTodoItem() = TodoItem(
        id = id,
        text = text,
        deadline = deadline,
        importance = importance,
        done = done,
        createdAt = createdAt,
        changedAt = changedAt,
    )

    companion object {
        const val TABLE_NAME = "todo_items"
    }

}


fun TodoItem.toDbItem() = TodoDbItem(
    id = id,
    text = text,
    deadline = deadline,
    importance = importance,
    done = done,
    createdAt = createdAt,
    changedAt = changedAt,
)