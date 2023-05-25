package ir.thatsmejavad.backgroundable.data.repository

import ir.thatsmejavad.backgroundable.model.media.Media

interface MediaRepository {
    suspend fun getPhoto(photoId: String): Media
}
