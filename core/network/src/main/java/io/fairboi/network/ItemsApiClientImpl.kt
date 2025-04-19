package io.fairboi.network

import android.util.Log
import io.fairboi.network.di.modules.ApiClientImplQualifier
import io.fairboi.network.di.modules.ApiClientScope
import io.fairboi.network.dto.TodoItemDto
import io.fairboi.network.dto.TodoItemRequestDto
import io.fairboi.network.dto.TodoItemResponseDto
import io.fairboi.network.dto.TodoListResponseDto
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.request
import io.ktor.client.request.setBody
import io.ktor.http.HttpMethod
import io.ktor.http.takeFrom
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import javax.inject.Inject

@ApiClientScope
internal class ItemsApiClientImpl @Inject constructor(
//    private val dataStore: KeyValueStorage,
    @ApiClientImplQualifier private val coroutineDispatcher: CoroutineDispatcher,
    @ApiClientImplQualifier private val client: HttpClient = mainHttpClient,
) : ItemsApiClient {

    private  val TAG = "ItemsApiClientImpl"
    override suspend fun getAllItems(): TodoListResponseDto = withContext(coroutineDispatcher) {
   runBlocking {
       val url = NetworkConstants.LIST_URL
       return@runBlocking  try {

           Log.d(TAG, "Getting items from $url")
           val response = client.get(url)
           Log.d(TAG, "Got items: ${response.body<TodoListResponseDto>()}")
           return@runBlocking response.body<TodoListResponseDto>()
           } catch (e: Exception) {
           Log.d(TAG, "Error getting items: ${e.message}")
           TodoListResponseDto(emptyList())

       }
   }



    }

    override suspend fun createItem(item: TodoItemRequestDto): TodoItemResponseDto = withContext(coroutineDispatcher) {
        val url = NetworkConstants.LIST_URL
        runBlocking {
            Log.d(TAG, "Creating item: $item")

            val response = client.request {
                url { it.takeFrom(url) }
                method = HttpMethod.Post
                setBody(item)
            }
            return@runBlocking response.body<TodoItemResponseDto>()
        }


    }

    override suspend fun updateItem(item: TodoItemRequestDto): TodoItemResponseDto = withContext(coroutineDispatcher) {
        val url = NetworkConstants.getItemUrl(item.item.id)
        runBlocking {
            val response = client.request {
                url { it.takeFrom(url) }
                method = HttpMethod.Put
                setBody(item)
            }
            return@runBlocking response.body<TodoItemResponseDto>()
        }
    }

    override suspend fun deleteItem(id: String):Unit = withContext(coroutineDispatcher) {
        val url = NetworkConstants.getItemUrl(id)
        runBlocking {
            client.request {
                url { it.takeFrom(url) }
                method = HttpMethod.Delete
            }

        }
    }

    override suspend fun refreshAll(items: List<TodoItemDto>): TodoListResponseDto {
        val urlString = NetworkConstants.LIST_URL
        val response = client.request {
            url { it.takeFrom(urlString) }
            method = HttpMethod.Put
            setBody(items)
        }


        return response.body<TodoListResponseDto>()
    }
}