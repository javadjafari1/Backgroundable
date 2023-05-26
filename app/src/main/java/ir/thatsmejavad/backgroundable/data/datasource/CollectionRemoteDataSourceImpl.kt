package ir.thatsmejavad.backgroundable.data.datasource

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import ir.thatsmejavad.backgroundable.core.Constants.COLLECTIONS_PER_PAGE_ITEM
import ir.thatsmejavad.backgroundable.data.PexelsApi
import ir.thatsmejavad.backgroundable.data.repository.CollectionsPagingSource
import ir.thatsmejavad.backgroundable.data.repository.MediaPagingSource
import ir.thatsmejavad.backgroundable.model.Collection
import ir.thatsmejavad.backgroundable.model.media.Media
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CollectionRemoteDataSourceImpl @Inject constructor(
    private val api: PexelsApi,
) : CollectionRemoteDataSource {
    override fun getCollections(): Flow<PagingData<Collection>> {
        return Pager(
            config = PagingConfig(
                pageSize = COLLECTIONS_PER_PAGE_ITEM,
            ),
            pagingSourceFactory = {
                CollectionsPagingSource(api)
            },
        ).flow
    }

    override fun getCollectionMedias(collectionId: String): Flow<PagingData<Media>> {
        return Pager(
            config = PagingConfig(
                pageSize = COLLECTIONS_PER_PAGE_ITEM,
            ),
            pagingSourceFactory = {
                MediaPagingSource(api = api, id = collectionId)
            },
        ).flow
    }
}
