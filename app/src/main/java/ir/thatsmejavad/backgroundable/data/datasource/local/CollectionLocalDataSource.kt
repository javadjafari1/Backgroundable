package ir.thatsmejavad.backgroundable.data.datasource.local

import androidx.paging.PagingSource
import ir.thatsmejavad.backgroundable.data.db.entity.CollectionEntity

interface CollectionLocalDataSource {
    suspend fun insertCollections(collections: List<CollectionEntity>)

    fun getPagedCollection(): PagingSource<Int, CollectionEntity>

    suspend fun deleteAll()
}
