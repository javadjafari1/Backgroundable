package ir.thatsmejavad.backgroundable.data.datasource.local

import ir.thatsmejavad.backgroundable.data.db.entity.ResourceEntity

interface ResourceLocalDataSource {

    suspend fun insertResources(resources: List<ResourceEntity>)

    suspend fun deleteAll()
}
