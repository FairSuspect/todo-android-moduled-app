package io.fairboi.list

import io.fairboi.domain.model.todo.TodoItem

internal data class TodoItemsUiState(
    val listState: ListState,
    val filterState: FilterState
) {
    sealed class ListState {
        data object Loading : ListState()
        data class Loaded(
            val items: List<TodoItem>,
            val filterState: FilterState = FilterState.ALL,
            val completedCount: Int,
        ) : ListState()

        data class Error(val message: String) : ListState()
    }

    companion object {
        val Initial = TodoItemsUiState(ListState.Loading, FilterState.ALL)
    }

    enum class FilterState(val filter: (TodoItem) -> Boolean) {
        ALL({ true }),
        NOT_COMPLETED({ !it.done })
    }
}


