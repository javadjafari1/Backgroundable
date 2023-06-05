package ir.thatsmejavad.backgroundable.data.datasource.remote

import ir.thatsmejavad.backgroundable.model.PagedResponse
import ir.thatsmejavad.backgroundable.model.media.Media

interface MediaRemoteDataSource {
    suspend fun getMedia(photoId: Int): Media

    suspend fun getMediasByCollectionId(collectionId: String, page: Int): PagedResponse<Media>
}
