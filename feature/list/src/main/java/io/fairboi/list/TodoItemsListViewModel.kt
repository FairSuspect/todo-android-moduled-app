package io.fairboi.list

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.fairboi.domain.model.todo.TodoId
import io.fairboi.domain.model.todo.TodoItem
import io.fairboi.domain.repositories.TodoItemsRepository
import jakarta.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

private const val TAG = "TodoItemsListViewModel"

class TodoItemsListViewModel @Inject constructor(
    private val todoRepository: TodoItemsRepository,
) : ViewModel() {
    private val _filterState = MutableStateFlow(TodoItemsUiState.FilterState.ALL)
    private val _uiState = MutableStateFlow(TodoItemsUiState.Initial)
    internal val uiState: StateFlow<TodoItemsUiState> = _uiState.asStateFlow()

    private fun getTodoItems() {
        viewModelScope.launch {
            todoRepository
                .getItemsFlow()
                .combine<List<TodoItem>, TodoItemsUiState.FilterState, TodoItemsUiState.ListState>(
                    _filterState
                ) { items, filterState ->

                    TodoItemsUiState.ListState.Loaded(
                        items = items.filter(filterState.filter),
                        filterState = filterState,
                        completedCount = items.count { it.done }
                    )
                }
                .catch {
                    TodoItemsUiState.ListState.Error(it.message ?: "Unknown error")
                }
                .stateIn(
                    viewModelScope,
                    SharingStarted.Eagerly,
                    TodoItemsUiState.ListState.Loading
                )
                .collect { todoItemsState ->
                    _uiState.update {
                        it.copy(
                            listState = todoItemsState,
                            filterState = _filterState.value,

                            )
                    }
                }
        }
    }

    init {
        getTodoItems()
    }

    private fun addTodoItem(todoItem: TodoItem) {
        viewModelScope.launch {
            todoRepository.addItem(todoItem)
        }
    }

    internal fun onTextTodoAdded(text: String) {
        val todoItem = TodoItem.fromText(text)
        addTodoItem(todoItem)
    }

    internal fun onItemChecked(todoItem: TodoItem) {
        viewModelScope.launch {
            todoRepository.updateItem(todoItem)
        }

    }

    internal fun onItemRemoved(itemId: TodoId) {
        viewModelScope.launch {
            todoRepository.deleteItemById(itemId)
        }
    }

    internal fun onFilterChanged(filterState: TodoItemsUiState.FilterState) {
        Log.d(TAG, "Updating filter: $filterState")
        viewModelScope.launch {
            _filterState.update {
                filterState
            }
        }
    }

    fun addTodoFromText(text: String)  {
        val todoItem = TodoItem.fromText(text)
        addTodoItem(todoItem)
    }
}

