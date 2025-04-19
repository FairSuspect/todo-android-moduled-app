package com.example.network

object NetworkConstants {
    internal const val BASE_URL = "https://0.0.0.0:8080"
    internal const val LIST_URL = "$BASE_URL/items"
    internal fun getItemUrl(id: String) = "$LIST_URL/$id"

}