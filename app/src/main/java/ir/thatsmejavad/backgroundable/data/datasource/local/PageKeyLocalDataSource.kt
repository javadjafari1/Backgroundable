package ir.thatsmejavad.backgroundable.data.datasource.local

import ir.thatsmejavad.backgroundable.data.db.entity.PageKeyEntity

interface PageKeyLocalDataSource {

    suspend fun insertPageKey(pageKey: PageKeyEntity)

    suspend fun getPageKeyById(id: String): PageKeyEntity?

    suspend fun deletePageKeyById(id: String)
}
