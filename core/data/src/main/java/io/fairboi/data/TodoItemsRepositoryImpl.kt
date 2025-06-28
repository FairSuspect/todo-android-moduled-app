package io.fairboi.data

import com.example.network.ItemsApiClient
import io.fairboi.data.di.DataScope
import io.fairboi.data.di.modules.SingleThreadBackgroundDispatcher
import io.fairboi.db.TodoLocalDataSource
import io.fairboi.db.toDbItem
import io.fairboi.domain.model.todo.TodoId
import io.fairboi.domain.model.todo.TodoItem
import io.fairboi.domain.repositories.TodoItemsRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@DataScope
class TodoItemsRepositoryImpl @Inject constructor(
    private val localDataSource: TodoLocalDataSource,
    private val apiClient: ItemsApiClient,

    @SingleThreadBackgroundDispatcher private val dispatcher: CoroutineDispatcher
) : TodoItemsRepository {


    override fun getItemsFlow(): Flow<List<TodoItem>> {

        return localDataSource.getItemsFlow().map { list -> list.map { it.toTodoItem() } }
    }

    override suspend fun getItems(): List<TodoItem> = withContext(dispatcher) {
        return@withContext localDataSource.getItems().map { it.toTodoItem() }
    }

    override suspend fun getItemById(id: TodoId): TodoItem? = withContext(dispatcher) {
        return@withContext localDataSource.getItemById(id)?.toTodoItem()
    }

    override suspend fun addItem(item: TodoItem) {
        CoroutineScope(dispatcher).launch {
            localDataSource.addItem(item.toDbItem())
        }
    }

    override suspend fun updateItem(item: TodoItem) {
        CoroutineScope(dispatcher).launch {
            localDataSource.updateItem(item.toDbItem())
        }
    }

    override suspend fun deleteItemById(id: TodoId) {
        CoroutineScope(dispatcher).launch {
            localDataSource.deleteItemById(id)
        }
    }

    override suspend fun deleteAll() {
        CoroutineScope(dispatcher).launch {
            localDataSource.deleteAll()
        }
    }

    override suspend fun addAll(items: List<TodoItem>) {
        CoroutineScope(dispatcher).launch {
            localDataSource.addAll(items.map { it.toDbItem() })
        }
    }
}