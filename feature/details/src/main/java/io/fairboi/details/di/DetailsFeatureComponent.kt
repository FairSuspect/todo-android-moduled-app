package io.fairboi.details.di

import dagger.Component
import io.fairboi.details.TodoDetailsViewModel
import io.fairboi.details.di.modules.RepositoryModule

@Component(
    modules = [
        RepositoryModule::class,
    ],
    dependencies = [
        DetailsFeatureDependencies::class
    ]
)
interface DetailsFeatureComponent {
    fun inject(dependencies: DetailsFeatureDependencies)

    @Component.Factory
    interface Factory {
        fun create(dependencies: DetailsFeatureDependencies): DetailsFeatureComponent
    }

    val viewModelFactory: TodoDetailsViewModel.Factory
}