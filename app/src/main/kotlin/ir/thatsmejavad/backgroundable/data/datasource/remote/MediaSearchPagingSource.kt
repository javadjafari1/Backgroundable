package ir.thatsmejavad.backgroundable.data.datasource.remote

import androidx.paging.PagingSource
import androidx.paging.PagingState
import ir.thatsmejavad.backgroundable.core.Constants.MEDIA_PER_PAGE_ITEM
import ir.thatsmejavad.backgroundable.core.bodyOrException
import ir.thatsmejavad.backgroundable.core.convertToRelativePath
import ir.thatsmejavad.backgroundable.data.api.PexelsApi
import ir.thatsmejavad.backgroundable.model.media.Media
import ir.thatsmejavad.backgroundable.model.media.Resources
import javax.inject.Inject

class MediaSearchPagingSource @Inject constructor(
    private val query: String,
    private val api: PexelsApi,
) : PagingSource<Int, Media>() {
    override fun getRefreshKey(state: PagingState<Int, Media>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)
                ?.prevKey
                ?.plus(1)
                ?: state
                    .closestPageToPosition(anchorPosition)
                    ?.nextKey
                    ?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Media> {
        return runCatching {
            val page = params.key ?: 1
            val response = api.searchPhoto(
                page = page,
                query = query
            )

            val body = response.bodyOrException()
            val total = body.total

            val mediasWithRelativePathInRes = body.data.map { media ->
                val res = Resources(
                    landscape = media.resources
                        .landscape
                        .convertToRelativePath()
                        .orEmpty(),
                    large = media.resources
                        .large
                        .convertToRelativePath()
                        .orEmpty(),
                    large2x = media.resources
                        .large2x
                        .convertToRelativePath()
                        .orEmpty(),
                    medium = media.resources
                        .medium
                        .convertToRelativePath()
                        .orEmpty(),
                    original = media.resources
                        .original
                        .convertToRelativePath()
                        .orEmpty(),
                    portrait = media.resources
                        .portrait
                        .convertToRelativePath()
                        .orEmpty(),
                    small = media.resources
                        .small
                        .convertToRelativePath()
                        .orEmpty(),
                    tiny = media.resources
                        .tiny
                        .convertToRelativePath()
                        .orEmpty()
                )
                media.copy(resources = res)
            }
            LoadResult.Page(
                data = mediasWithRelativePathInRes,
                prevKey = if (page == 1) null else page.minus(1),
                nextKey = if (page * MEDIA_PER_PAGE_ITEM > total) null else page.plus(1),
            )
        }.getOrElse {
            LoadResult.Error(it)
        }
    }
}
