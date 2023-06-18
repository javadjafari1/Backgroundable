package ir.thatsmejavad.backgroundable.data.datasource.remote

import androidx.paging.PagingSource
import androidx.paging.PagingState
import ir.thatsmejavad.backgroundable.core.Constants.MEDIA_PER_PAGE_ITEM
import ir.thatsmejavad.backgroundable.core.bodyOrException
import ir.thatsmejavad.backgroundable.data.api.PexelsApi
import ir.thatsmejavad.backgroundable.model.media.Media
import javax.inject.Inject

class MediaSearchPagingSource @Inject constructor(
    private val query: String,
    private val api: PexelsApi,
) : PagingSource<Int, Media>() {
    override fun getRefreshKey(state: PagingState<Int, Media>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Media> {
        return try {
            val page = params.key ?: 1
            val response = api.searchPhoto(
                page = page,
                query = query
            )

            val body = response.bodyOrException()
            val total = body.total

            LoadResult.Page(
                data = body.data,
                prevKey = if (page == 1) null else page.minus(1),
                nextKey = if (page * MEDIA_PER_PAGE_ITEM > total) null else page.plus(1),
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}
