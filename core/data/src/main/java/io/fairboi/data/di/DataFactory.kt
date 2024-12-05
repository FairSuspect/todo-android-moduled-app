package io.fairboi.data.di

import io.fairboi.domain.repositories.TodoItemsRepository


class  DataFactory(
    deps: DataDependencies,
) {
    private val component: DataComponent = DaggerDataComponent.factory().create(deps)

    fun createItemsRepository(): TodoItemsRepository = component.todoItemsRepository



}