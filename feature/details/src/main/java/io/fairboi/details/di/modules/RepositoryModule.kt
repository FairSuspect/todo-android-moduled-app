package io.fairboi.details.di.modules

import dagger.Module
import dagger.Provides
import io.fairboi.data.di.DataFactory
import io.fairboi.domain.repositories.TodoItemsRepository

@Module
interface RepositoryModule {

    companion object {

        @Provides
        fun bindTodoItemsRepository(factory: DataFactory): TodoItemsRepository =
            factory.createItemsRepository()
    }

}