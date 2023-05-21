package ir.thatsmejavad.backgroundable.data.repository

import androidx.paging.PagingData
import ir.thatsmejavad.backgroundable.data.datasource.CollectionRemoteDataSource
import ir.thatsmejavad.backgroundable.model.Collection
import ir.thatsmejavad.backgroundable.model.Media
import ir.thatsmejavad.backgroundable.model.PagedResponse
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CollectionRepositoryImpl @Inject constructor(
    private val collectionRemoteDataSource: CollectionRemoteDataSource
) : CollectionRepository {
    override fun getCollections(): Flow<PagingData<Collection>> {
        return collectionRemoteDataSource.getCollections()
    }

    override suspend fun getCollectionMedias(collectionId: String): PagedResponse<Media> {
        return collectionRemoteDataSource.getCollectionMedias(collectionId)
    }
}
