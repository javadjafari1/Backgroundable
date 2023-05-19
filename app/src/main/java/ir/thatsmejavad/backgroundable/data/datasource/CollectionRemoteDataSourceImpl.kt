package ir.thatsmejavad.backgroundable.data.datasource

import ir.thatsmejavad.backgroundable.core.bodyOrException
import ir.thatsmejavad.backgroundable.data.PexelsApi
import ir.thatsmejavad.backgroundable.model.Collection
import ir.thatsmejavad.backgroundable.model.Media
import ir.thatsmejavad.backgroundable.model.PagedResponse
import javax.inject.Inject

class CollectionRemoteDataSourceImpl @Inject constructor(
    private val api: PexelsApi,
) : CollectionRemoteDataSource {
    override suspend fun getCollections(): PagedResponse<Collection> {
        return api.getCollections().bodyOrException()
    }

    override suspend fun getCollectionMedias(collectionId: String): PagedResponse<Media> {
        return api.getMediasByCollectionId(collectionId).bodyOrException()
    }
}
