package ir.thatsmejavad.backgroundable.di.modules

import dagger.Binds
import dagger.Module
import ir.thatsmejavad.backgroundable.data.datasource.remote.MediaRemoteDataSource
import ir.thatsmejavad.backgroundable.data.datasource.remote.MediaRemoteDataSourceImpl
import ir.thatsmejavad.backgroundable.data.repository.MediaRepository
import ir.thatsmejavad.backgroundable.data.repository.MediaRepositoryImpl

@Module
interface MediaModule {
    @Binds
    fun bindMediaRepository(impl: MediaRepositoryImpl): MediaRepository

    @Binds
    fun bindMediaRemoteDataSource(
        impl: MediaRemoteDataSourceImpl
    ): MediaRemoteDataSource
}
