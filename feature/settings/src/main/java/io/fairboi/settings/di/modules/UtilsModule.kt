package io.fairboi.settings.di.modules

import dagger.Module
import dagger.Provides
import io.fairboi.domain.repositories.SettingsRepository
import io.fairboi.utils.di.UtilsFactory

@Module
internal interface UtilsModule {
    companion object {
        @Provides
        fun settingsRepository(factory: UtilsFactory): SettingsRepository =
            factory.createSettingsRepository()
    }
}