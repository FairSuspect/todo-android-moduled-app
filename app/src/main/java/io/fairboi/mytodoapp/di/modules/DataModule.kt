package io.fairboi.mytodoapp.di.modules

import dagger.Module
import dagger.Provides
import io.fairboi.data.di.DataFactory
import io.fairboi.mytodoapp.di.AppComponent
import io.fairboi.mytodoapp.di.AppScope

@Module
internal interface DataModule {
    companion object {
        @AppScope
        @Provides
        fun dataFactory(deps: AppComponent): DataFactory =
            DataFactory(deps)

    }
}