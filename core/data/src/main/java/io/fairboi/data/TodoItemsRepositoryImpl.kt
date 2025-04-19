package io.fairboi.data

import android.util.Log
import io.fairboi.data.di.DataScope
import io.fairboi.data.di.modules.SingleThreadBackgroundDispatcher
import io.fairboi.db.TodoLocalDataSource
import io.fairboi.db.toDbItem
import io.fairboi.domain.model.NetworkState
import io.fairboi.domain.model.todo.TodoId
import io.fairboi.domain.model.todo.TodoItem
import io.fairboi.domain.repositories.TodoItemsRepository
import io.fairboi.network.ItemsApiClient
import io.fairboi.network.dto.toDto
import io.fairboi.network.dto.toRequestDto
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@DataScope
class TodoItemsRepositoryImpl @Inject constructor(
    private val localDataSource: TodoLocalDataSource, private val apiClient: ItemsApiClient,

    @SingleThreadBackgroundDispatcher private val dispatcher: CoroutineDispatcher
) : TodoItemsRepository {
    private val TAG = "TodoItemsRepositoryImpl"

    private val _networkStateFlow: MutableStateFlow<NetworkState> =
        MutableStateFlow(NetworkState.Success)



    override fun getItemsFlow(): Flow<List<TodoItem>> {
        CoroutineScope(dispatcher).launch {
            try {
                Log.d(TAG, "Updating items")
                updateItems()
            } catch (e: Exception) {
                _networkStateFlow.update { NetworkState.Error.UnknownError }
            }
        }
        return localDataSource.getItemsFlow().map { list -> list.map { it.toTodoItem() } }
    }

    suspend fun updateItems(): Unit = withContext(dispatcher) {

        _networkStateFlow.update { NetworkState.Updating }
        val remoteItemsResponse = apiClient.getAllItems()
        val todoItems = remoteItemsResponse.list.map { it.toTodoItem() }
        Log.d(TAG, "Remote items: $todoItems")

        val dbItems = localDataSource.getItems()

        // Merging local and remote lists
        val mergedItems = (dbItems.map { it.toTodoItem() } + todoItems).distinctBy { it.id }

        val mergedDbItems = mergedItems.map { it.toDbItem() }
        localDataSource.refreshItems(mergedDbItems)
        _networkStateFlow.update { NetworkState.Success }
        Log.d(TAG, "Items updated")
        // Сырое решение для первого раза. Можно улучшить за счёт ревизий и дат обновлений
        if (mergedItems.size != todoItems.size) {
            apiClient.refreshAll(mergedItems.map { it.toDto() })
        }
    }

    override suspend fun getItems(): List<TodoItem> = withContext(dispatcher) {
        return@withContext localDataSource.getItems().map { it.toTodoItem() }
    }

    override suspend fun getItemById(id: TodoId): TodoItem? = withContext(dispatcher) {
        return@withContext localDataSource.getItemById(id)?.toTodoItem()
    }

    override suspend fun addItem(item: TodoItem) {
        CoroutineScope(dispatcher).launch {
            apiClient.createItem(item.toRequestDto())
            localDataSource.addItem(item.toDbItem())
        }
    }

    override suspend fun updateItem(item: TodoItem) {
        CoroutineScope(dispatcher).launch {

            apiClient.updateItem(item.toRequestDto())
            localDataSource.updateItem(item.toDbItem())
        }
    }

    override suspend fun deleteItemById(id: TodoId) {
        CoroutineScope(dispatcher).launch {
            apiClient.deleteItem(id)
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