package ir.thatsmejavad.backgroundable.data.datasource.remote

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import ir.thatsmejavad.backgroundable.core.Constants.COLLECTIONS_PER_PAGE_ITEM
import ir.thatsmejavad.backgroundable.core.RemotePagingSource
import ir.thatsmejavad.backgroundable.core.bodyOrException
import ir.thatsmejavad.backgroundable.data.api.PexelsApi
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
                RemotePagingSource(
                    pageSize = COLLECTIONS_PER_PAGE_ITEM,
                    apiCall = { page ->
                        api.getCollections(page = page).bodyOrException()
                    },
                )
            },
        ).flow
    }

    override fun getCollectionMedias(collectionId: String): Flow<PagingData<Media>> {
        return Pager(
            config = PagingConfig(
                pageSize = COLLECTIONS_PER_PAGE_ITEM,
            ),
            pagingSourceFactory = {
                RemotePagingSource(
                    pageSize = COLLECTIONS_PER_PAGE_ITEM,
                    apiCall = { page ->
                        api.getMediasByCollectionId(
                            page = page,
                            collectionId = collectionId
                        ).bodyOrException()
                    },
                )
            },
        ).flow
    }
}
