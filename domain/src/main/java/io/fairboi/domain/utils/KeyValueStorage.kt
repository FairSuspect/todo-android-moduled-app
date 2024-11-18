package io.fairboi.domain.utils

interface KeyValueStorage {
    suspend fun save(key: String, value: String)

    suspend fun get(key: String): String?

    suspend fun remove(key: String)
}