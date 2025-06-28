package com.example.network

import com.example.network.di.modules.ApiClientImplQualifier
import com.example.network.di.modules.ApiClientScope
import com.example.network.dto.TodoItemRequestDto
import com.example.network.dto.TodoListResponseDto
import io.fairboi.domain.utils.KeyValueStorage
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

@ApiClientScope
internal class ItemsApiClientImpl @Inject constructor(
    private val dataStore: KeyValueStorage,
    @ApiClientImplQualifier private val coroutineDispatcher: CoroutineDispatcher,
    @ApiClientImplQualifier private val client: HttpClient = mainHttpClient,
) : ItemsApiClient {
    override suspend fun getAllItems(): TodoListResponseDto {
        val url = NetworkConstants.LIST_URL
       val response = client.get(url)
        return response.body<TodoListResponseDto>()
    }

    override suspend fun createItem(item: TodoItemRequestDto): TodoListResponseDto {
        TODO("Not yet implemented")
    }

    override suspend fun updateItem(item: TodoItemRequestDto): TodoListResponseDto {
        TODO("Not yet implemented")
    }

    override suspend fun deleteItem(id: String): TodoListResponseDto {
        TODO("Not yet implemented")
    }
}