package ir.thatsmejavad.backgroundable.data.repository

import ir.thatsmejavad.backgroundable.data.datasource.CollectionRemoteDataSource
import ir.thatsmejavad.backgroundable.model.Collection
import ir.thatsmejavad.backgroundable.model.Media
import ir.thatsmejavad.backgroundable.model.PagedResponse
import javax.inject.Inject

class CollectionRepositoryImpl @Inject constructor(
    private val collectionRemoteDataSource: CollectionRemoteDataSource
) : CollectionRepository {
    override suspend fun getCollections(): PagedResponse<Collection> {
        return collectionRemoteDataSource.getCollections()
    }

    override suspend fun getCollectionMedias(collectionId: String): PagedResponse<Media> {
        return collectionRemoteDataSource.getCollectionMedias(collectionId)
    }
}
