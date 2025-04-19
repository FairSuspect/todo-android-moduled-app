package io.fairboi.network.di

import io.fairboi.network.ItemsApiClientImpl
import io.fairboi.network.di.modules.ApiClientModule
import io.fairboi.network.di.modules.ApiClientScope
import dagger.Component

@ApiClientScope
@Component(dependencies = [NetworkDependencies::class], modules = [ApiClientModule::class])
internal interface NetworkComponent {
    @Component.Factory
    interface Factory {
        fun create(deps: NetworkDependencies): NetworkComponent
    }

    val apiClientImpl: ItemsApiClientImpl
}