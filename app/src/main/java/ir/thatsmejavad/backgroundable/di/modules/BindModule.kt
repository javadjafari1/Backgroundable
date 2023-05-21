package ir.thatsmejavad.backgroundable.di.modules

import dagger.Binds
import dagger.Module
import ir.thatsmejavad.backgroundable.data.datasource.CollectionRemoteDataSource
import ir.thatsmejavad.backgroundable.data.datasource.CollectionRemoteDataSourceImpl
import ir.thatsmejavad.backgroundable.data.datasource.MediaRemoteDataSource
import ir.thatsmejavad.backgroundable.data.datasource.MediaRemoteDataSourceImpl
import ir.thatsmejavad.backgroundable.data.repository.CollectionRepository
import ir.thatsmejavad.backgroundable.data.repository.CollectionRepositoryImpl
import ir.thatsmejavad.backgroundable.data.repository.MediaRepository
import ir.thatsmejavad.backgroundable.data.repository.MediaRepositoryImpl

@Module
interface BindModule {
    @Binds
    fun bindCollectionRepository(impl: CollectionRepositoryImpl): CollectionRepository

    @Binds
    fun bindMediaRepository(impl: MediaRepositoryImpl): MediaRepository

    @Binds
    fun bindCollectionRemoteDataSource(
        impl: CollectionRemoteDataSourceImpl
    ): CollectionRemoteDataSource

    @Binds
    fun bindMediaRemoteDataSource(
        impl: MediaRemoteDataSourceImpl
    ): MediaRemoteDataSource
}
