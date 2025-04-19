package io.fairboi.data.di

import io.fairboi.network.di.NetworkDependencies
import dagger.Component
import io.fairboi.data.TodoItemsRepositoryImpl
import io.fairboi.data.di.modules.CoroutinesModule
import io.fairboi.data.di.modules.DatabaseModule
import io.fairboi.data.di.modules.NetworkModule
import io.fairboi.data.di.modules.RepositoriesModule
import io.fairboi.db.di.DatabaseDependencies
import io.fairboi.db.di.DatabaseScope
import io.fairboi.network.di.modules.ApiClientScope
import jakarta.inject.Scope

@Scope
annotation class DataScope

@Component(
    dependencies = [DataDependencies::class],
    modules = [
        CoroutinesModule::class,
        DatabaseModule::class,
        NetworkModule::class,
        RepositoriesModule::class,
    ]
)

@DataScope
@DatabaseScope
@ApiClientScope
internal interface DataComponent : DatabaseDependencies, NetworkDependencies {

    @Component.Factory
    interface Factory {
        fun create(dependencies: DataDependencies): DataComponent
    }

    val todoItemsRepository: TodoItemsRepositoryImpl



}