package ir.thatsmejavad.backgroundable.data.datasource.remote

import androidx.paging.PagingData
import ir.thatsmejavad.backgroundable.model.PagedResponse
import ir.thatsmejavad.backgroundable.model.media.Media
import kotlinx.coroutines.flow.Flow

interface MediaRemoteDataSource {
    suspend fun getMedia(photoId: Int): Media

    suspend fun getMediasByCollectionId(collectionId: String, page: Int): PagedResponse<Media>

    fun searchPhoto(query: String): Flow<PagingData<Media>>
}
