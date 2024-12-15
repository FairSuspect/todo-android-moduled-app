package io.fairboi.mytodoapp.di

import android.content.Context
import dagger.BindsInstance
import dagger.Component
import io.fairboi.data.di.DataDependencies
import io.fairboi.data.di.DataScope
import io.fairboi.details.di.DetailsFeatureDependencies
import io.fairboi.domain.repositories.SettingsRepository
import io.fairboi.list.di.ListFeatureDependencies
import io.fairboi.mytodoapp.TodoApplication
import io.fairboi.mytodoapp.di.modules.DataModule
import io.fairboi.mytodoapp.di.modules.ListFeatureModule
import io.fairboi.mytodoapp.di.modules.SettingsFeatureModule
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
        DataModule::class,
        SettingsFeatureModule::class,
        ListFeatureModule::class,
    ]
)
@AppSettingsScope
@AppScope
@DataScope
internal interface AppComponent :
    SettingsFeatureDependencies,
    ListFeatureDependencies,
    DataDependencies,
    UtilsDependencies,
    DetailsFeatureDependencies
{
    @Component.Factory
    interface Factory {
        fun create(
            @BindsInstance context: Context
        ): AppComponent
    }

    fun inject(application: TodoApplication)

    fun listFeatureComponent(): TodoListFeatureComponent

    fun detailsFeatureComponent(): DetailsFeatureComponent

    fun settingsFeatureComponent(): SettingsFeatureComponent

    fun settingsRepository(): SettingsRepository
}