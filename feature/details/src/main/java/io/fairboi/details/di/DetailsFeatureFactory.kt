package io.fairboi.details.di

import io.fairboi.details.TodoDetailsViewModel

class DetailsFeatureFactory(deps: DetailsFeatureDependencies) {
    private val component: DetailsFeatureComponent =
        DaggerDetailsFeatureComponent.factory().create(deps)

    fun createViewModelFactory(): TodoDetailsViewModel.Factory =
        component.viewModelFactory
}