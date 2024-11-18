package io.fairboi.mytodoapp.di

import dagger.Subcomponent
import io.fairboi.mytodoapp.di.modules.SettingsFeatureModule
import io.fairboi.settings.SettingsViewModel

@Subcomponent(modules = [SettingsFeatureModule::class])
internal interface SettingsFeatureComponent {
    val settingsViewModel: SettingsViewModel
}