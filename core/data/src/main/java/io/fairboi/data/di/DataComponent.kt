package io.fairboi.data.di

import dagger.Component
import io.fairboi.data.di.modules.CoroutinesModule
import io.fairboi.data.di.modules.DatabaseModule
import io.fairboi.data.di.modules.RepositoriesModule
import io.fairboi.db.di.DatabaseDependencies
import io.fairboi.db.di.DatabaseScope
import io.fairboi.domain.repositories.TodoItemsRepository
import jakarta.inject.Scope

@Scope
internal annotation class DataScope

@Component(
    dependencies = [DataDependencies::class],
    modules = [
        CoroutinesModule::class,
        DatabaseModule::class,
        RepositoriesModule::class,
    ]
)

@DataScope
@DatabaseScope
internal interface DataComponent : DatabaseDependencies {

    @Component.Factory
    interface Factory {
        fun create(dependencies: DataDependencies): DataComponent
    }

    fun todoItemsRepository(): TodoItemsRepository

}