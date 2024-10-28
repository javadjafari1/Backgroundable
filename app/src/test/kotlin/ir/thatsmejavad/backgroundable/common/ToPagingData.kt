package ir.thatsmejavad.backgroundable.common

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.testing.asPagingSourceFactory
import ir.thatsmejavad.backgroundable.core.Constants.MEDIA_PER_PAGE_ITEM
import kotlinx.coroutines.flow.Flow

fun <T : Any> List<T>.toPagingData(): Flow<PagingData<T>> {
    return Pager(
        config = PagingConfig(MEDIA_PER_PAGE_ITEM),
        pagingSourceFactory = this.asPagingSourceFactory()
    ).flow
}
