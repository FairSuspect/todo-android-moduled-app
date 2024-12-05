package io.fairboi.list.di

import dagger.Component
import io.fairboi.list.TodoItemsListViewModel
import io.fairboi.list.di.modules.RepositoryModule

@Component(
    dependencies = [ListFeatureDependencies::class],
    modules = [RepositoryModule::class]
)
internal interface ListFeatureComponent {

    @Component.Factory
    interface Factory {
        fun create(dependencies: ListFeatureDependencies): ListFeatureComponent
    }

    val viewModel: TodoItemsListViewModel
}