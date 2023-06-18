package ir.thatsmejavad.backgroundable.data.repository

import androidx.paging.PagingData
import ir.thatsmejavad.backgroundable.data.db.relation.MediaWithResources
import ir.thatsmejavad.backgroundable.model.media.Media
import kotlinx.coroutines.flow.Flow

interface MediaRepository {

    fun getMediasByCollectionId(collectionId: String): Flow<PagingData<MediaWithResources>>

    suspend fun getMediaWithResources(id: Int): MediaWithResources?

    fun searchPhoto(query: String): Flow<PagingData<Media>>
}
