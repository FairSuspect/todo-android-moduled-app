package io.fairboi.data.di.modules

import dagger.Module
import dagger.Provides
import io.fairboi.data.di.DataComponent
import io.fairboi.db.di.DatabaseFactory



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