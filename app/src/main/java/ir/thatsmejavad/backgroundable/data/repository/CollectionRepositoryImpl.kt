package ir.thatsmejavad.backgroundable.data.repository

import androidx.paging.PagingData
import ir.thatsmejavad.backgroundable.data.datasource.local.CollectionLocalDataSource
import ir.thatsmejavad.backgroundable.data.datasource.remote.CollectionRemoteDataSource
import ir.thatsmejavad.backgroundable.model.Collection
import ir.thatsmejavad.backgroundable.model.media.Media
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CollectionRepositoryImpl @Inject constructor(
    private val collectionRemoteDataSource: CollectionRemoteDataSource,
    private val collectionLocalDataSource: CollectionLocalDataSource,
) : CollectionRepository {
    override fun getCollections(): Flow<PagingData<Collection>> {
        return collectionRemoteDataSource.getCollections()
    }

    override fun getCollectionMedias(collectionId: String): Flow<PagingData<Media>> {
        return collectionRemoteDataSource.getCollectionMedias(collectionId)
    }
}
