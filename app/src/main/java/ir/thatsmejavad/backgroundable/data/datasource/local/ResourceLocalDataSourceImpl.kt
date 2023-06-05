package ir.thatsmejavad.backgroundable.data.datasource.local

import ir.thatsmejavad.backgroundable.data.db.dao.ResourceDao
import ir.thatsmejavad.backgroundable.data.db.entity.ResourceEntity
import javax.inject.Inject

class ResourceLocalDataSourceImpl @Inject constructor(
    private val resourceDao: ResourceDao,
) : ResourceLocalDataSource {
    override suspend fun insertResources(resources: List<ResourceEntity>) {
        resourceDao.insertResources(resources)
    }

    override suspend fun deleteAll() {
        resourceDao.deleteAll()
    }
}