package ir.thatsmejavad.backgroundable.data.datasource.remote

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import ir.thatsmejavad.backgroundable.data.datasource.local.CollectionLocalDataSource
import ir.thatsmejavad.backgroundable.data.db.BackgroundableDatabase
import ir.thatsmejavad.backgroundable.data.db.entity.CollectionEntity
import ir.thatsmejavad.backgroundable.model.Collection

@OptIn(ExperimentalPagingApi::class)
class CollectionRemoteMediator(
    private val collectionRemoteDataSource: CollectionRemoteDataSource,
    private val collectionLocalDataSource: CollectionLocalDataSource,
    private val database: BackgroundableDatabase,
    private val shouldFetch: Boolean
) : RemoteMediator<Int, CollectionEntity>() {

    override suspend fun initialize(): InitializeAction {
        return if (shouldFetch) {
            InitializeAction.LAUNCH_INITIAL_REFRESH
        } else {
            InitializeAction.SKIP_INITIAL_REFRESH
        }
    }

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, CollectionEntity>
    ): MediatorResult {
        return try {
            val loadKey = when (loadType) {
                LoadType.PREPEND -> {
                    return MediatorResult.Success(endOfPaginationReached = true)
                }

                LoadType.APPEND, LoadType.REFRESH -> nextPage
            }
            val response = collectionRemoteDataSource
                .getCollections(page = loadKey)
            nextPage++
            database.withTransaction {
                if (loadType == LoadType.REFRESH) {
                    collectionLocalDataSource.deleteAll()
                }

                collectionLocalDataSource.insertCollections(response.data.map(Collection::toEntity))
            }

            MediatorResult.Success(
                endOfPaginationReached = (response.page * response.perPage >= response.total)
            )
        } catch (e: Exception) {
            MediatorResult.Error(e)
        }
    }

    companion object {
        var nextPage = 1
    }
}
