package io.fairboi.mytodoapp.di.modules

import dagger.Module
import dagger.Provides
import io.fairboi.details.di.DetailsFeatureFactory
import io.fairboi.mytodoapp.di.AppComponent

@Module
internal interface DetailsFeatureModule {
    companion object {
        @Provides
        fun detailsFeatureFactory(depsImpl: AppComponent): DetailsFeatureFactory =
            DetailsFeatureFactory(depsImpl)
    }


}