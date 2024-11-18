package io.fairboi.utils.di

import io.fairboi.domain.repositories.SettingsRepository
import io.fairboi.domain.utils.KeyValueStorage


class UtilsFactory(
    deps: UtilsDependencies,
) {
    private val component: UtilsComponent = DaggerUtilsComponent.factory().create(deps)

    fun createKeyValueStorage(name: String): KeyValueStorage =
        component.keyValueStorageFactory.create(name)

    fun createSettingsRepository(): SettingsRepository = component.settingsRepositoryImpl
}

