package io.fairboi.data.di.modules

import dagger.Module
import dagger.Provides
import jakarta.inject.Qualifier
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi

@Qualifier
internal annotation class SingleThreadBackgroundDispatcher

@Qualifier
internal annotation class BackgroundDispatcher

@Module
internal interface CoroutinesModule {
    companion object {
        @Provides
        @BackgroundDispatcher
        fun backgroundDispatcher(): CoroutineDispatcher =
            Dispatchers.IO

        @OptIn(ExperimentalCoroutinesApi::class)
        @Provides
        @SingleThreadBackgroundDispatcher
        fun backgroundOneTreadDispatcher(): CoroutineDispatcher =
            Dispatchers.IO.limitedParallelism(1)
    }
}