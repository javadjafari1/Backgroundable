package ir.thatsmejavad.backgroundable.data.datasource

import ir.thatsmejavad.backgroundable.model.Collection
import ir.thatsmejavad.backgroundable.model.Media
import ir.thatsmejavad.backgroundable.model.PagedResponse

interface CollectionRemoteDataSource {

    suspend fun getCollections(): PagedResponse<Collection>

    suspend fun getCollectionMedias(collectionId: String): PagedResponse<Media>
}
