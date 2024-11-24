package io.fairboi.data.di.modules

import dagger.Binds
import dagger.Module
import io.fairboi.data.TodoItemsRepositoryImpl
import io.fairboi.domain.repositories.TodoItemsRepository

@Module
internal interface RepositoriesModule {
    @Binds
    fun todoItemsRepository(impl: TodoItemsRepositoryImpl): TodoItemsRepository
}