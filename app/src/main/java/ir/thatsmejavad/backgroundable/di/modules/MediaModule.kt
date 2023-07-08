package ir.thatsmejavad.backgroundable.di.modules

import dagger.Binds
import dagger.Module
import ir.thatsmejavad.backgroundable.core.AndroidDownloader
import ir.thatsmejavad.backgroundable.core.Downloader
import ir.thatsmejavad.backgroundable.data.datasource.local.MediaLocalDataSource
import ir.thatsmejavad.backgroundable.data.datasource.local.MediaLocalDataSourceImpl
import ir.thatsmejavad.backgroundable.data.datasource.local.ResourceLocalDataSource
import ir.thatsmejavad.backgroundable.data.datasource.local.ResourceLocalDataSourceImpl
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

    @Binds
    fun bindMediaLocalDataSource(
        impl: MediaLocalDataSourceImpl
    ): MediaLocalDataSource

    @Binds
    fun bindResourceLocalDataSource(
        impl: ResourceLocalDataSourceImpl
    ): ResourceLocalDataSource

    @Binds
    fun bindAndroidDownloader(
        impl: AndroidDownloader
    ): Downloader
}
