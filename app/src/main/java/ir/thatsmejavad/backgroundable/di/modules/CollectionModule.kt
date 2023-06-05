package ir.thatsmejavad.backgroundable.di.modules

import dagger.Binds
import dagger.Module
import ir.thatsmejavad.backgroundable.data.datasource.local.CollectionLocalDataSource
import ir.thatsmejavad.backgroundable.data.datasource.local.CollectionLocalDataSourceImpl
import ir.thatsmejavad.backgroundable.data.datasource.remote.CollectionRemoteDataSource
import ir.thatsmejavad.backgroundable.data.datasource.remote.CollectionRemoteDataSourceImpl
import ir.thatsmejavad.backgroundable.data.repository.CollectionRepository
import ir.thatsmejavad.backgroundable.data.repository.CollectionRepositoryImpl

@Module(
    includes = [
        DatabaseModule::class
    ]
)
interface CollectionModule {
    @Binds
    fun bindCollectionRepository(impl: CollectionRepositoryImpl): CollectionRepository

    @Binds
    fun bindCollectionRemoteDataSource(
        impl: CollectionRemoteDataSourceImpl
    ): CollectionRemoteDataSource

    @Binds
    fun bindCollectionLocalDataSource(
        impl: CollectionLocalDataSourceImpl
    ): CollectionLocalDataSource
}
