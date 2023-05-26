package ir.thatsmejavad.backgroundable.data.datasource

import ir.thatsmejavad.backgroundable.model.media.Media

interface MediaRemoteDataSource {
    suspend fun getPhoto(photoId: String): Media
}
