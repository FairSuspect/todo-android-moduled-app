package io.fairboi.mytodoapp.di

import dagger.Subcomponent
import io.fairboi.details.di.DetailsFeatureFactory
import io.fairboi.mytodoapp.di.modules.DetailsFeatureModule

@Subcomponent(modules = [DetailsFeatureModule::class])
internal interface DetailsFeatureComponent {

    fun detailsFeatureFactory(): DetailsFeatureFactory
}