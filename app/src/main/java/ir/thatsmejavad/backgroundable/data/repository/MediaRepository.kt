package ir.thatsmejavad.backgroundable.data.repository

import ir.thatsmejavad.backgroundable.model.media.Media

interface MediaRepository {
    suspend fun getMedia(mediaId: Int): Media
}
