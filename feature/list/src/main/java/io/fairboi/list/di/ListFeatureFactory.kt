package io.fairboi.list.di

import io.fairboi.list.TodoItemsListViewModel

class ListFeatureFactory(deps: ListFeatureDependencies)  {
    private val component: ListFeatureComponent = DaggerListFeatureComponent.factory().create(deps)

    fun createViewModel(): TodoItemsListViewModel = component.viewModel
}