package io.fairboi.mytodoapp.di.modules

import dagger.Module
import dagger.Provides
import io.fairboi.domain.repositories.SettingsRepository
import io.fairboi.mytodoapp.di.AppComponent
import io.fairboi.mytodoapp.di.AppScope
import io.fairboi.utils.di.UtilsFactory

@Module
internal interface UtilsModule {
    companion object {
        @Provides
        @AppScope
        fun factory(depsImpl: AppComponent): UtilsFactory = UtilsFactory(depsImpl)

        @Provides
        fun settingsHandler(factory: UtilsFactory): SettingsRepository =
            factory.createSettingsRepository()
    }
}