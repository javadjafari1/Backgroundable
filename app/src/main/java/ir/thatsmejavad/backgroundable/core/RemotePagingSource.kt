package ir.thatsmejavad.backgroundable.core

import androidx.paging.PagingSource
import androidx.paging.PagingState
import ir.thatsmejavad.backgroundable.model.PagedResponse
import javax.inject.Inject

class RemotePagingSource<T : Any> @Inject constructor(
    private val pageSize: Int,
    private val apiCall: suspend (Int) -> PagedResponse<T>
) : PagingSource<Int, T>() {
    override fun getRefreshKey(state: PagingState<Int, T>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, T> {
        return try {
            val page = params.key ?: 1

            val pagedResponse = apiCall(page)
            val total = pagedResponse.total
            LoadResult.Page(
                data = pagedResponse.data,
                prevKey = if (page == 1) null else page.minus(1),
                nextKey = if (page * pageSize > total) null else page.plus(1),
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}
