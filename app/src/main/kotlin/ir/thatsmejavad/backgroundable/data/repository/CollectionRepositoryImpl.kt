package ir.thatsmejavad.backgroundable.data.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import ir.thatsmejavad.backgroundable.core.Constants.COLLECTIONS_PER_PAGE_ITEM
import ir.thatsmejavad.backgroundable.data.datasource.local.CollectionLocalDataSource
import ir.thatsmejavad.backgroundable.data.datasource.local.PageKeyLocalDataSource
import ir.thatsmejavad.backgroundable.data.datasource.remote.CollectionRemoteDataSource
import ir.thatsmejavad.backgroundable.data.datasource.remote.CollectionRemoteMediator
import ir.thatsmejavad.backgroundable.data.db.BackgroundableDatabase
import ir.thatsmejavad.backgroundable.data.db.entity.CollectionEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CollectionRepositoryImpl @Inject constructor(
    private val collectionRemoteDataSource: CollectionRemoteDataSource,
    private val collectionLocalDataSource: CollectionLocalDataSource,
    private val database: BackgroundableDatabase,
    private val pageKeyLocalDataSource: PageKeyLocalDataSource,
) : CollectionRepository {
    @OptIn(ExperimentalPagingApi::class)
    override fun getCollections(): Flow<PagingData<CollectionEntity>> {
        return Pager(
            config = PagingConfig(
                pageSize = COLLECTIONS_PER_PAGE_ITEM,
            ),
            remoteMediator = CollectionRemoteMediator(
                collectionRemoteDataSource = collectionRemoteDataSource,
                collectionLocalDataSource = collectionLocalDataSource,
                database = database,
                pageKeyLocalDataSource = pageKeyLocalDataSource,
            ),
            pagingSourceFactory = {
                collectionLocalDataSource.getPagedCollection()
            }
        ).flow
    }
}
