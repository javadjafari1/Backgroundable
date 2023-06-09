package ir.thatsmejavad.backgroundable.data.datasource.local

import ir.thatsmejavad.backgroundable.data.db.dao.PageKeyDao
import ir.thatsmejavad.backgroundable.data.db.entity.PageKeyEntity
import javax.inject.Inject

class PageKeyLocalDataSourceImpl @Inject constructor(
    private val pageKeyDao: PageKeyDao,
) : PageKeyLocalDataSource {

    override suspend fun insertPageKey(pageKey: PageKeyEntity) {
        pageKeyDao.insertPageKey(pageKey)
    }

    override suspend fun getPageKeyById(id: String): PageKeyEntity? {
        return pageKeyDao.getPageKeyById(id)
    }

    override suspend fun deletePageKeyById(id: String) {
        pageKeyDao.deletePageKeyById(id)
    }
}
