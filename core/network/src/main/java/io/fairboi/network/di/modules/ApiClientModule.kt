package io.fairboi.network.di.modules

import io.fairboi.network.mainHttpClient
import dagger.Module
import dagger.Provides
import io.ktor.client.HttpClient
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Qualifier
import javax.inject.Scope


@Scope
annotation class ApiClientScope

@Qualifier
annotation class ApiClientImplQualifier

@Module
internal interface ApiClientModule {
    companion object {
        @OptIn(ExperimentalCoroutinesApi::class)
        @Provides
        @ApiClientImplQualifier
        fun coroutineDispatcher(): CoroutineDispatcher =
            Dispatchers.IO.limitedParallelism(1)

        @Provides
        @ApiClientImplQualifier
        fun httpClient(): HttpClient =
            mainHttpClient
    }
}