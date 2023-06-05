package ir.thatsmejavad.backgroundable.data.repository

import androidx.paging.PagingData
import ir.thatsmejavad.backgroundable.data.db.entity.CollectionEntity
import kotlinx.coroutines.flow.Flow

interface CollectionRepository {
    fun getCollections(shouldFetch: Boolean): Flow<PagingData<CollectionEntity>>
}
