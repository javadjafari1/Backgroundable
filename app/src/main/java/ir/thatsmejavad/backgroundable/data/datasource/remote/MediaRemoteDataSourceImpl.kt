package ir.thatsmejavad.backgroundable.data.datasource.remote

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import ir.thatsmejavad.backgroundable.core.Constants.MEDIA_PER_PAGE_ITEM
import ir.thatsmejavad.backgroundable.core.bodyOrException
import ir.thatsmejavad.backgroundable.data.api.PexelsApi
import ir.thatsmejavad.backgroundable.model.PagedResponse
import ir.thatsmejavad.backgroundable.model.media.Media
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MediaRemoteDataSourceImpl @Inject constructor(
    private val api: PexelsApi,
) : MediaRemoteDataSource {
    override suspend fun getMedia(photoId: Int): Media {
        return api.getPhoto(photoId).bodyOrException()
    }

    override suspend fun getMediasByCollectionId(
        collectionId: String,
        page: Int
    ): PagedResponse<Media> {
        return api.getMediasByCollectionId(
            collectionId = collectionId,
            page = page
        ).bodyOrException()
    }

    override fun searchPhoto(
        query: String,
    ): Flow<PagingData<Media>> {
        return Pager(
            config = PagingConfig(
                pageSize = MEDIA_PER_PAGE_ITEM,
                initialLoadSize = MEDIA_PER_PAGE_ITEM,
                prefetchDistance = MEDIA_PER_PAGE_ITEM
            ),
            pagingSourceFactory = {
                MediaSearchPagingSource(
                    query = query,
                    api = api,
                )
            },
        ).flow
    }
}
