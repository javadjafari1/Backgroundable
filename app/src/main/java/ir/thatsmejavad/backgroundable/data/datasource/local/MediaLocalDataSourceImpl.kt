package ir.thatsmejavad.backgroundable.data.datasource.local

import androidx.paging.PagingSource
import ir.thatsmejavad.backgroundable.data.db.dao.MediaDao
import ir.thatsmejavad.backgroundable.data.db.entity.MediaEntity
import ir.thatsmejavad.backgroundable.data.db.relation.MediaWithResources
import javax.inject.Inject

class MediaLocalDataSourceImpl @Inject constructor(
    private val mediaDao: MediaDao,
) : MediaLocalDataSource {
    override suspend fun insertMedias(medias: List<MediaEntity>) {
        mediaDao.insertMedias(medias)
    }

    override fun getPagedCollection(collectionId: String): PagingSource<Int, MediaWithResources> {
        return mediaDao.getPagedCollection(collectionId)
    }

    override suspend fun deleteAll() {
        mediaDao.deleteAll()
    }
}
