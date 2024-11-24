package io.fairboi.db.di

import dagger.Component
import io.fairboi.db.TodoItemDao
import jakarta.inject.Scope

@Scope
annotation class DatabaseScope

@Component(dependencies = [DatabaseDependencies::class])
internal interface DatabaseComponent {
    @Component.Factory
    interface Factory{
        fun create(deps: DatabaseDependencies): DatabaseComponent


    }

    val dao: TodoItemDao
}