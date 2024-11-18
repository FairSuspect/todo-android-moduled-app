package io.fairboi.settings.di

import dagger.Component
import io.fairboi.settings.di.modules.CoroutineModule
import io.fairboi.settings.di.modules.UtilsModule
import io.fairboi.settings.SettingsViewModel

@Component(
    dependencies = [SettingsFeatureDependencies::class],
    modules = [UtilsModule::class, CoroutineModule::class],
)
internal interface SettingsFeatureComponent {
    @Component.Factory
    interface Factory {
        fun create(dependencies: SettingsFeatureDependencies): SettingsFeatureComponent
    }

    val viewModel: SettingsViewModel
}