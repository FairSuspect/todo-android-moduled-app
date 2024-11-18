package io.fairboi.settings.di

import io.fairboi.settings.SettingsViewModel


class SettingsFeatureFactory(deps: SettingsFeatureDependencies) {
    private val component: SettingsFeatureComponent =
        DaggerSettingsFeatureComponent.factory().create(deps)

    fun createViewModel(): SettingsViewModel = component.viewModel
}