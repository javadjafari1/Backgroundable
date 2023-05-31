package ir.thatsmejavad.backgroundable.di.modules

import dagger.Binds
import dagger.Module
import ir.thatsmejavad.backgroundable.data.datasource.CollectionRemoteDataSource
import ir.thatsmejavad.backgroundable.data.datasource.CollectionRemoteDataSourceImpl
import ir.thatsmejavad.backgroundable.data.repository.CollectionRepository
import ir.thatsmejavad.backgroundable.data.repository.CollectionRepositoryImpl

@Module
interface CollectionModule {
    @Binds
    fun bindCollectionRepository(impl: CollectionRepositoryImpl): CollectionRepository

    @Binds
    fun bindCollectionRemoteDataSource(
        impl: CollectionRemoteDataSourceImpl
    ): CollectionRemoteDataSource
}
