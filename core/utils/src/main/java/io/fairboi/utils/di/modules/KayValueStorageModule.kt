package io.fairboi.utils.di.modules

import dagger.Module
import dagger.Provides
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Qualifier

@Qualifier
internal annotation class DataStoreQualifier

@Module
internal interface KeyValueStorageModule {
    companion object {
        @Provides
        @DataStoreQualifier
        fun coroutineDispatcher(): CoroutineDispatcher =
            Dispatchers.IO
    }
}