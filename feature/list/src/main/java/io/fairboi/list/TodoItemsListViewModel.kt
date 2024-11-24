package io.fairboi.list

import androidx.lifecycle.ViewModel
import jakarta.inject.Inject
import io.fairboi.domain.repositories.TodoItemsRepository

class TodoItemsListViewModel @Inject constructor(
    private val todoRepository: TodoItemsRepository,
) : ViewModel() {

//    val todoItems = todoRepository.getTodoItems()

}