package ir.thatsmejavad.backgroundable.data.repository

import androidx.paging.PagingSource
import androidx.paging.PagingState
import ir.thatsmejavad.backgroundable.core.Constants.COLLECTIONS_PER_PAGE_ITEM
import ir.thatsmejavad.backgroundable.core.bodyOrException
import ir.thatsmejavad.backgroundable.data.PexelsApi
import ir.thatsmejavad.backgroundable.model.Collection
import javax.inject.Inject

class CollectionsPagingSource @Inject constructor(
    private val api: PexelsApi,
) : PagingSource<Int, Collection>() {
    override fun getRefreshKey(state: PagingState<Int, Collection>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Collection> {
        return try {
            val page = params.key ?: 1
            val response = api.getCollections(page = page)

            val body = response.bodyOrException()
            val total = body.total
            LoadResult.Page(
                data = body.data,
                prevKey = if (page == 1) null else page.minus(1),
                nextKey = if (page * COLLECTIONS_PER_PAGE_ITEM > total) null else page.plus(1),
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}
