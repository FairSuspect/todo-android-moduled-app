package io.fairboi.network.di

import io.fairboi.network.ItemsApiClient

class NetworkFactory(
    deps: NetworkDependencies,
) {
    private val component: NetworkComponent = DaggerNetworkComponent.factory().create(deps)

    fun createApiClient(): ItemsApiClient = component.apiClientImpl

}