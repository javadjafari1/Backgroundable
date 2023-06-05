package ir.thatsmejavad.backgroundable.data.datasource.local

import androidx.paging.PagingSource
import ir.thatsmejavad.backgroundable.data.db.dao.CollectionDao
import ir.thatsmejavad.backgroundable.data.db.entity.CollectionEntity
import javax.inject.Inject

class CollectionLocalDataSourceImpl @Inject constructor(
    private val collectionDao: CollectionDao
) : CollectionLocalDataSource {
    override suspend fun insertCollections(collections: List<CollectionEntity>) {
        collectionDao.insertCollections(collections)
    }

    override fun getPagedCollection(): PagingSource<Int, CollectionEntity> {
        return collectionDao.getPagedCollection()
    }

    override suspend fun deleteAll() {
        collectionDao.deleteAll()
    }
}
