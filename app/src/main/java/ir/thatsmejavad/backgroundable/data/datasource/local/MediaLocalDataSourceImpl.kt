package ir.thatsmejavad.backgroundable.data.datasource.local

import androidx.paging.PagingSource
import ir.thatsmejavad.backgroundable.data.db.dao.MediaDao
import ir.thatsmejavad.backgroundable.data.db.dao.PageKeyDao
import ir.thatsmejavad.backgroundable.data.db.entity.MediaEntity
import ir.thatsmejavad.backgroundable.data.db.entity.PageKeyEntity
import ir.thatsmejavad.backgroundable.data.db.relation.MediaWithResources
import javax.inject.Inject

class MediaLocalDataSourceImpl @Inject constructor(
    private val mediaDao: MediaDao,
    private val pageKeyDao: PageKeyDao,
) : MediaLocalDataSource {
    override suspend fun insertMedias(medias: List<MediaEntity>) {
        mediaDao.insertMedias(medias)
    }

    override fun getPagedMedia(collectionId: String): PagingSource<Int, MediaWithResources> {
        return mediaDao.getPagedMedia(collectionId)
    }

    override suspend fun getMediaWithResources(id: Int): MediaWithResources {
        return mediaDao.getMediaWithResources(id)
    }

    override suspend fun deleteAll() {
        mediaDao.deleteAll()
    }

    override suspend fun insertPageKey(pageKey: PageKeyEntity) {
        pageKeyDao.insertPageKey(pageKey)
    }

    override suspend fun getPageKeyById(id: String): PageKeyEntity {
        return pageKeyDao.getPageKeyById(id)
    }

    override suspend fun deletePageKeyById(id: String) {
        pageKeyDao.deletePageKeyById(id)
    }

    override suspend fun deleteById(id: String) {
        mediaDao.deleteById(id)
    }
}
