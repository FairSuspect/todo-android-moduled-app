package io.fairboi.db.di

import dagger.Component
import io.fairboi.db.TodoItemDao
import io.fairboi.db.di.modules.RoomModule
import jakarta.inject.Scope

@Scope
annotation class DatabaseScope

@DatabaseScope
@Component(dependencies = [DatabaseDependencies::class], modules = [RoomModule::class])
internal interface DatabaseComponent {
    @Component.Factory
    interface Factory{
        fun create(deps: DatabaseDependencies): DatabaseComponent


    }

    val dao: TodoItemDao
}