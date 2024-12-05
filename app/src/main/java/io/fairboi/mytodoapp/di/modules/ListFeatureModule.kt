package io.fairboi.mytodoapp.di.modules

import dagger.Module
import dagger.Provides
import io.fairboi.list.di.ListFeatureFactory
import io.fairboi.mytodoapp.di.AppComponent

@Module
internal interface ListFeatureModule {
companion object{
    @Provides
    fun listFeatureFactory(depsImpl: AppComponent): ListFeatureFactory =
        ListFeatureFactory(depsImpl)
    @Provides
    fun listFeatureViewModel(factory: ListFeatureFactory) = factory.createViewModel()
}

}