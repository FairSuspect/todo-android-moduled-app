package io.fairboi.details

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import io.fairboi.domain.model.todo.TodoId
import io.fairboi.domain.model.todo.TodoItem
import io.fairboi.domain.repositories.TodoItemsRepository
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
const val TAG = "TodoDetailsViewModel"
class TodoDetailsViewModel @AssistedInject
constructor(
    @Assisted
    todoId: TodoId?,
    private val todoRepository: TodoItemsRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow<TodoDetailsUiState>(TodoDetailsUiState.Loading)
    internal val uiState = _uiState.asStateFlow()
    private val coroutineExceptionHandler = CoroutineExceptionHandler { _, exception ->
        _uiState.update { TodoDetailsUiState.Error(exception.message ?: "Unknown error") }
    }


    init {
        getTodoItem(todoId)

    }

    fun getTodoItem(todoId: TodoId?) {
        viewModelScope.launch(coroutineExceptionHandler) {
            _uiState.update {
                TodoDetailsUiState.Loading
            }

            // Получение задания будет происходить, только если todoId не null
            val item =
                todoId?.let {
                    todoRepository.getItemById(todoId)
                }
            _uiState.update {
                // При нормальных обстоятельствах item == null, когда todoId == null
                // Поэтому мы можем считать, что если item == null, то мы в режиме создания
                if (item == null) {
                    val blankTodoItem = TodoItem.blank()

                    TodoDetailsUiState.Loaded(
                        blankTodoItem,
                        todoEditMode = TodoDetailsUiState.TodoEditMode.NEW
                    )
                } else {
                    TodoDetailsUiState.Loaded(
                        item,
                        todoEditMode = TodoDetailsUiState.TodoEditMode.EDIT
                    )
                }

            }

        }
    }

    fun edit(todoItem: TodoItem) {
        viewModelScope.launch {
            _uiState.update {
                if(it is TodoDetailsUiState.Loaded) {
                   return@update TodoDetailsUiState.Loaded(todoItem, it.todoEditMode)
                }
                return@update it

            }
        }
    }

    fun save(todoItem: TodoItem) {
        viewModelScope.launch {

            val isNewTask =
                uiState.value is TodoDetailsUiState.Loaded && (uiState.value as TodoDetailsUiState.Loaded).todoEditMode == TodoDetailsUiState.TodoEditMode.NEW

            try {
                if (isNewTask) {
                    todoRepository.addItem(todoItem)
                } else {
                    todoRepository.updateItem(todoItem)
                }
            } catch (e: Exception) {
                Log.e(TAG, "Error saving todo item", e)
                _uiState.update {
                    TodoDetailsUiState.Loaded(todoItem)
                }
            }
        }

    }

    fun delete(todoItem: TodoItem) {
        viewModelScope.launch {
            _uiState.update {
                TodoDetailsUiState.Loading
            }
            try {
                todoRepository.deleteItemById(todoItem.id)
            } catch (e: Exception) {
                Log.e(TAG, "Error deleting todo item", e)
                _uiState.update {
                    TodoDetailsUiState.Loaded(todoItem)
                }
            }
        }
    }

    fun tryAgain(todoId: TodoId?) {
        getTodoItem(todoId)

    }

    @AssistedFactory
    interface Factory {
        fun create(todoId: TodoId?): TodoDetailsViewModel

    }
}