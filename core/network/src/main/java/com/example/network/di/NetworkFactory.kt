package com.example.network.di

import com.example.network.ItemsApiClient

class NetworkFactory(
    deps: NetworkDependencies,
) {
    private val component: NetworkComponent = DaggerNetworkComponent.factory().create(deps)

    fun createApiClient(): ItemsApiClient = component.apiClientImpl
}