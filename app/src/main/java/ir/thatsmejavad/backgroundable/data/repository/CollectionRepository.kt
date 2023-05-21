package ir.thatsmejavad.backgroundable.data.repository

import androidx.paging.PagingData
import ir.thatsmejavad.backgroundable.model.Collection
import ir.thatsmejavad.backgroundable.model.Media
import ir.thatsmejavad.backgroundable.model.PagedResponse
import kotlinx.coroutines.flow.Flow

interface CollectionRepository {
    fun getCollections(): Flow<PagingData<Collection>>

    suspend fun getCollectionMedias(collectionId: String): PagedResponse<Media>
}
