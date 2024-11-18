package io.fairboi.mytodoapp.di.modules

import dagger.Module
import dagger.Provides
import io.fairboi.mytodoapp.di.AppComponent
import io.fairboi.settings.di.SettingsFeatureFactory

@Module
internal interface SettingsFeatureModule {
    companion object {
        @Provides
        fun settingsFeatureFactory(depsImpl: AppComponent): SettingsFeatureFactory =
            SettingsFeatureFactory(depsImpl)

        @Provides
        fun settingsViewModel(factory: SettingsFeatureFactory): io.fairboi.settings.SettingsViewModel =
            factory.createViewModel()
    }
}