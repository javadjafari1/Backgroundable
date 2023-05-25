package ir.thatsmejavad.backgroundable.data.repository

import androidx.paging.PagingData
import ir.thatsmejavad.backgroundable.model.Collection
import ir.thatsmejavad.backgroundable.model.media.Media
import kotlinx.coroutines.flow.Flow

interface CollectionRepository {
    fun getCollections(): Flow<PagingData<Collection>>

    fun getCollectionMedias(collectionId: String): Flow<PagingData<Media>>
}
