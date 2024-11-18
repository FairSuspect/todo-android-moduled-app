package io.fairboi.mytodoapp.di

import android.content.Context
import dagger.BindsInstance
import dagger.Component
import io.fairboi.domain.repositories.SettingsRepository
import io.fairboi.mytodoapp.TodoApplication
import io.fairboi.mytodoapp.di.modules.UtilsModule
import io.fairboi.settings.di.SettingsFeatureDependencies
import io.fairboi.utils.di.UtilsDependencies
import io.fairboi.utils.di.modules.AppSettingsScope

import javax.inject.Scope

@Scope
annotation class AppScope

@Component(
    modules = [
        UtilsModule::class,
    ]
)
@AppSettingsScope
@AppScope
internal interface AppComponent :
    SettingsFeatureDependencies,
    UtilsDependencies {
    @Component.Factory
    interface Factory {
        fun create(
            @BindsInstance context: Context
        ): AppComponent
    }

    fun inject(application: TodoApplication)

    fun settingsFeatureComponent(): SettingsFeatureComponent

    fun settingsRepository(): SettingsRepository
}