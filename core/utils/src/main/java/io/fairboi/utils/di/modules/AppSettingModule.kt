package io.fairboi.utils.di.modules

import dagger.Module
import dagger.Provides
import io.fairboi.domain.utils.KeyValueStorage
import io.fairboi.utils.DataStoreSaver
import io.fairboi.utils.SettingsRepositoryImpl
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Qualifier
import javax.inject.Scope

@Scope
annotation class AppSettingsScope

@Qualifier
internal annotation class AppSettingsQualifier

@Module
internal interface AppSettingsModule {
    companion object {
        @Provides
        @AppSettingsScope
        @AppSettingsQualifier
        fun keyValueDataSaver(factory: DataStoreSaver.Factory): KeyValueStorage =
            factory.create(SettingsRepositoryImpl.SETTINGS_KEY)

        @Provides
        @AppSettingsScope
        @AppSettingsQualifier
        fun coroutineDispatcher(): CoroutineDispatcher = Dispatchers.IO
    }
}