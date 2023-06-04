package ir.thatsmejavad.backgroundable.data.repository

import ir.thatsmejavad.backgroundable.data.datasource.remote.MediaRemoteDataSource
import ir.thatsmejavad.backgroundable.model.media.Media
import javax.inject.Inject

class MediaRepositoryImpl @Inject constructor(
    private val mediaRemoteDataSource: MediaRemoteDataSource,
) : MediaRepository {
    override suspend fun getMedia(mediaId: Int): Media {
        return mediaRemoteDataSource.getMedia(mediaId)
    }
}
