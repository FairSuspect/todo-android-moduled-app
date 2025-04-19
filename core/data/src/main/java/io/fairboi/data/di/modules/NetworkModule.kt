package io.fairboi.data.di.modules

import io.fairboi.network.ItemsApiClient
import io.fairboi.network.di.NetworkFactory
import dagger.Module
import dagger.Provides
import io.fairboi.data.di.DataComponent

@Module
internal interface NetworkModule {
    companion object {
        @Provides
        fun networkFactory(depsImpl: DataComponent): NetworkFactory = NetworkFactory(depsImpl)

        @Provides
        fun networkClient(factory: NetworkFactory): ItemsApiClient = factory.createApiClient()

    }
}