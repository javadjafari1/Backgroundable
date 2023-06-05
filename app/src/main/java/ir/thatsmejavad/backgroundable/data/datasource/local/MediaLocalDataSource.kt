package ir.thatsmejavad.backgroundable.data.datasource.local

import androidx.paging.PagingSource
import ir.thatsmejavad.backgroundable.data.db.entity.MediaEntity
import ir.thatsmejavad.backgroundable.data.db.relation.MediaWithResources

interface MediaLocalDataSource {
    suspend fun insertMedias(medias: List<MediaEntity>)

    fun getPagedMedia(collectionId: String): PagingSource<Int, MediaWithResources>

    suspend fun getMediaWithResources(id: Int): MediaWithResources

    suspend fun deleteAll()
}
