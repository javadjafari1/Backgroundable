package ir.thatsmejavad.backgroundable.data.datasource.remote

import ir.thatsmejavad.backgroundable.core.bodyOrException
import ir.thatsmejavad.backgroundable.data.api.PexelsApi
import ir.thatsmejavad.backgroundable.model.media.Media
import javax.inject.Inject

class MediaRemoteDataSourceImpl @Inject constructor(
    private val api: PexelsApi,
) : MediaRemoteDataSource {
    override suspend fun getMedia(photoId: Int): Media {
        return api.getPhoto(photoId).bodyOrException()
    }
}
