package io.fairboi.list.di.modules

import dagger.Module
import dagger.Provides
import io.fairboi.data.di.DataFactory
import io.fairboi.domain.repositories.TodoItemsRepository

@Module
interface RepositoryModule {
    companion object {
        @Provides
        fun itemsRepository(factory: DataFactory): TodoItemsRepository =
            factory.createItemsRepository()
    }
}