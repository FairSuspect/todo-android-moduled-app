package io.fairboi.details

import io.fairboi.domain.model.todo.TodoItem

internal sealed class TodoDetailsUiState {

        data object Loading : TodoDetailsUiState()
        data class Loaded(
            val item: TodoItem,
            val todoEditMode: TodoEditMode = TodoEditMode.NEW,
        ) : TodoDetailsUiState()

        data class Error(val message: String) : TodoDetailsUiState()


    enum class TodoEditMode {
        NEW,
        EDIT,
    }
}
