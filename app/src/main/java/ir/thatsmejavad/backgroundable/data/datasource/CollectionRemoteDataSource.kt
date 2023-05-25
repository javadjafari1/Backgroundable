package ir.thatsmejavad.backgroundable.data.datasource

import androidx.paging.PagingData
import ir.thatsmejavad.backgroundable.model.Collection
import ir.thatsmejavad.backgroundable.model.media.Media
import kotlinx.coroutines.flow.Flow

interface CollectionRemoteDataSource {

    fun getCollections(): Flow<PagingData<Collection>>

    fun getCollectionMedias(collectionId: String): Flow<PagingData<Media>>
}
