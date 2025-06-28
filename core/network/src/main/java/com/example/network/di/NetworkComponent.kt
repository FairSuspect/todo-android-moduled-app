package com.example.network.di

import com.example.network.ItemsApiClientImpl
import com.example.network.di.modules.ApiClientModule
import com.example.network.di.modules.ApiClientScope
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