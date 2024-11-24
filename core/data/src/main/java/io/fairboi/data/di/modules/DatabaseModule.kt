package io.fairboi.data.di.modules

import dagger.Module
import dagger.Provides
import io.fairboi.data.di.DataComponent
import io.fairboi.db.di.DatabaseFactory
import jakarta.inject.Qualifier
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi


/// В этом файле описаны зависимости базы данных
/// В модуле с помощью @Provides мы объясняем даггеру как создавать зависимости
@Module
internal interface DatabaseModule {
    companion object {
        @Provides
        fun databaseFactory(depsImpl: DataComponent): DatabaseFactory = DatabaseFactory(depsImpl)

        @Provides
        fun localDataSource(factory: DatabaseFactory) = factory.createLocalClient()


    }
}