package com.example.network.di

import io.fairboi.domain.utils.KeyValueStorage

interface NetworkDependencies {
    val keyValueDataSaver: KeyValueStorage
}