package ir.thatsmejavad.backgroundable.data.datasource

import ir.thatsmejavad.backgroundable.core.bodyOrException
import ir.thatsmejavad.backgroundable.data.PexelsApi
import ir.thatsmejavad.backgroundable.model.media.Media
import javax.inject.Inject

class MediaRemoteDataSourceImpl @Inject constructor(
    private val api: PexelsApi,
) : MediaRemoteDataSource {
    override suspend fun getPhoto(photoId: String): Media {
        return api.getPhoto(photoId).bodyOrException()
    }
}
