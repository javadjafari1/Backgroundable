package ir.thatsmejavad.backgroundable.data.datasource.remote

import androidx.paging.PagingData
import ir.thatsmejavad.backgroundable.model.Collection
import ir.thatsmejavad.backgroundable.model.PagedResponse
import ir.thatsmejavad.backgroundable.model.media.Media
import kotlinx.coroutines.flow.Flow

interface CollectionRemoteDataSource {

    suspend fun getCollections(page: Int): PagedResponse<Collection>

    fun getCollectionMedias(collectionId: String): Flow<PagingData<Media>>
}
