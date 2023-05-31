package ir.thatsmejavad.backgroundable.data.datasource

import ir.thatsmejavad.backgroundable.model.media.Media

interface MediaRemoteDataSource {
    suspend fun getMedia(photoId: Int): Media
}
