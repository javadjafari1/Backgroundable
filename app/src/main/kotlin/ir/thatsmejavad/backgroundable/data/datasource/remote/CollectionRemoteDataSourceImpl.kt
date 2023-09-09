package ir.thatsmejavad.backgroundable.data.datasource.remote

import ir.thatsmejavad.backgroundable.core.bodyOrException
import ir.thatsmejavad.backgroundable.data.api.PexelsApi
import ir.thatsmejavad.backgroundable.model.Collection
import ir.thatsmejavad.backgroundable.model.PagedResponse
import javax.inject.Inject

class CollectionRemoteDataSourceImpl @Inject constructor(
    private val api: PexelsApi,
) : CollectionRemoteDataSource {
    override suspend fun getCollections(page: Int): PagedResponse<Collection> {
        return api.getCollections(page = page).bodyOrException()
    }
}
