package io.fairboi.db.di

import io.fairboi.db.TodoLocalDataSource

class DatabaseFactory(
    deps: DatabaseDependencies
) {
    private val component: DatabaseComponent = DaggerDatabaseComponent.factory().create(deps)

    fun createLocalClient(): TodoLocalDataSource = component.dao
}