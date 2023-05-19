package ir.thatsmejavad.backgroundable.data.datasource

import ir.thatsmejavad.backgroundable.model.Media

interface MediaRemoteDataSource {
    suspend fun getPhoto(photoId: String): Media
}
