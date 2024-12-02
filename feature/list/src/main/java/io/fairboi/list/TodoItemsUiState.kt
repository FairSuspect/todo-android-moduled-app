package io.fairboi.list

import io.fairboi.domain.model.todo.TodoItem

internal data class TodoItemsUiState(
    val todoItemsState: TodoItemsState
){
    sealed class TodoItemsState{
        data object Loading : TodoItemsState()
        data class Loaded(val items: List<TodoItem>) : TodoItemsState()
        data class Error(val message: String) : TodoItemsState()
    }

    companion object{
        val Initial = TodoItemsUiState(TodoItemsState.Loading)
    }
}


