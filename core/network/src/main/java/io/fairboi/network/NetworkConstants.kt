package io.fairboi.network

object NetworkConstants {
    internal const val BASE_URL = "http://192.168.10.2:8080"
    internal const val LIST_URL = "$BASE_URL/todos"
    internal fun getItemUrl(id: String) = "$LIST_URL/$id"

}