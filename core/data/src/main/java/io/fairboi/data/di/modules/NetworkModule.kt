package io.fairboi.data.di.modules

import com.example.network.ItemsApiClient
import com.example.network.di.NetworkFactory
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