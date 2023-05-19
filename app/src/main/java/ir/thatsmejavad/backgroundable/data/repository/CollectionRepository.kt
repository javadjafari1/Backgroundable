package ir.thatsmejavad.backgroundable.data.repository

import ir.thatsmejavad.backgroundable.model.Collection
import ir.thatsmejavad.backgroundable.model.Media
import ir.thatsmejavad.backgroundable.model.PagedResponse

interface CollectionRepository {
    suspend fun getCollections(): PagedResponse<Collection>

    suspend fun getCollectionMedias(collectionId: String): PagedResponse<Media>
}
