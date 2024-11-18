package io.fairboi.utils.di

import dagger.Component
import io.fairboi.utils.DataStoreSaver
import io.fairboi.utils.SettingsRepositoryImpl
import io.fairboi.utils.di.modules.AppSettingsModule
import io.fairboi.utils.di.modules.AppSettingsScope
import io.fairboi.utils.di.modules.KeyValueStorageModule


@Component(
    dependencies = [UtilsDependencies::class],
    modules = [
        KeyValueStorageModule::class,
        AppSettingsModule::class,
    ]
)
@AppSettingsScope
internal interface UtilsComponent {
    @Component.Factory
    interface Factory {
        fun create(deps: UtilsDependencies): UtilsComponent
    }

    val keyValueStorageFactory: DataStoreSaver.Factory

    val settingsRepositoryImpl: SettingsRepositoryImpl
}