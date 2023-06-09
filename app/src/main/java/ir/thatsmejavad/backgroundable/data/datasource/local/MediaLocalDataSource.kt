package ir.thatsmejavad.backgroundable.data.datasource.local

import androidx.paging.PagingSource
import ir.thatsmejavad.backgroundable.data.db.entity.MediaEntity
import ir.thatsmejavad.backgroundable.data.db.entity.PageKeyEntity
import ir.thatsmejavad.backgroundable.data.db.relation.MediaWithResources

interface MediaLocalDataSource {
    suspend fun insertMedias(medias: List<MediaEntity>)

    fun getPagedMedia(collectionId: String): PagingSource<Int, MediaWithResources>

    suspend fun getMediaWithResources(id: Int): MediaWithResources

    suspend fun deleteAll()

    suspend fun insertPageKey(pageKey: PageKeyEntity)

    suspend fun getPageKeyById(id: String): PageKeyEntity

    suspend fun deletePageKeyById(id: String)

    suspend fun deleteById(id: String)
}
