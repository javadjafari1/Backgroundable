package ir.thatsmejavad.backgroundable.data.repository

import ir.thatsmejavad.backgroundable.data.datasource.MediaRemoteDataSource
import ir.thatsmejavad.backgroundable.model.Media
import javax.inject.Inject

class MediaRepositoryImpl @Inject constructor(
    private val mediaRemoteDataSource: MediaRemoteDataSource,
) : MediaRepository {
    override suspend fun getPhoto(photoId: String): Media {
        return mediaRemoteDataSource.getPhoto(photoId)
    }
}
