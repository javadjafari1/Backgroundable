package ir.thatsmejavad.backgroundable.data.repository

import androidx.paging.PagingData
import ir.thatsmejavad.backgroundable.data.db.relation.MediaWithResources
import ir.thatsmejavad.backgroundable.model.media.Media
import kotlinx.coroutines.flow.Flow

interface MediaRepository {
    suspend fun getMedia(mediaId: Int): Media

    fun getMediasByCollectionId(
        collectionId: String,
        shouldFetch: Boolean,
    ): Flow<PagingData<MediaWithResources>>

    suspend fun getMediaWithResources(id: Int): MediaWithResources
}
