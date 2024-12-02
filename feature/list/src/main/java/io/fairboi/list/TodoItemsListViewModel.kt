package io.fairboi.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.fairboi.domain.model.todo.TodoItem
import jakarta.inject.Inject
import io.fairboi.domain.repositories.TodoItemsRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

internal class TodoItemsListViewModel @Inject constructor(
    private val todoRepository: TodoItemsRepository,
) : ViewModel() {
    private val _uiState = MutableStateFlow(TodoItemsUiState.Initial)
    val uiState: StateFlow<TodoItemsUiState> = _uiState.asStateFlow()

    private fun getTodoItems() {
        viewModelScope.launch {
            todoRepository.getItemsFlow()
                .map {
                    TodoItemsUiState.TodoItemsState.Loaded(it)
                }.catch {
                    TodoItemsUiState.TodoItemsState.Error(it.message ?: "Unknown error")
                }.collect { todoItemsState ->
                    _uiState.update {
                        it.copy(
                            todoItemsState = todoItemsState
                        )
                    }
                }
        }
    }

    private  fun addTodoItem( todoItem: TodoItem) {
        viewModelScope.launch{
            todoRepository.addItem(todoItem)
        }
    }

    internal  fun onTextTodoAdded(text:String) {
        val todoItem = TodoItem.fromText(text)
        addTodoItem(todoItem)
    }

//    val todoItems = todoRepository.getTodoItems()

}

