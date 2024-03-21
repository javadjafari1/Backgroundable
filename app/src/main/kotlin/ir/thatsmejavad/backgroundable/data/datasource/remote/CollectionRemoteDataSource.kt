package ir.thatsmejavad.backgroundable.data.datasource.remote

import ir.thatsmejavad.backgroundable.model.Collection
import ir.thatsmejavad.backgroundable.model.PagedResponse

interface CollectionRemoteDataSource {
    suspend fun getCollections(page: Int): PagedResponse<Collection>
}
