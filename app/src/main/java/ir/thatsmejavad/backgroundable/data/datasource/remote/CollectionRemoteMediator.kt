package ir.thatsmejavad.backgroundable.data.datasource.remote

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import ir.thatsmejavad.backgroundable.core.Constants.COLLECTION_REFRESH_TIME_IN_HOUR
import ir.thatsmejavad.backgroundable.data.datasource.local.CollectionLocalDataSource
import ir.thatsmejavad.backgroundable.data.datasource.local.PageKeyLocalDataSource
import ir.thatsmejavad.backgroundable.data.db.BackgroundableDatabase
import ir.thatsmejavad.backgroundable.data.db.entity.CollectionEntity
import ir.thatsmejavad.backgroundable.data.db.entity.PageKeyEntity
import ir.thatsmejavad.backgroundable.model.Collection
import java.util.concurrent.TimeUnit
import kotlin.math.ceil

@OptIn(ExperimentalPagingApi::class)
class CollectionRemoteMediator(
    private val collectionRemoteDataSource: CollectionRemoteDataSource,
    private val collectionLocalDataSource: CollectionLocalDataSource,
    private val database: BackgroundableDatabase,
    private val pageKeyLocalDataSource: PageKeyLocalDataSource,
) : RemoteMediator<Int, CollectionEntity>() {

    private var pageKeyEntity: PageKeyEntity? = null

    override suspend fun initialize(): InitializeAction {
        pageKeyEntity = pageKeyLocalDataSource.getPageKeyById(COLLECTION_ID)

        val lastUpdateTime = pageKeyEntity?.timestamp ?: 0
        val elapsedHours =
            TimeUnit.MILLISECONDS.toHours(System.currentTimeMillis() - lastUpdateTime)

        return if (lastUpdateTime == -1L || elapsedHours > COLLECTION_REFRESH_TIME_IN_HOUR) {
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
            val nextPage = when (loadType) {
                LoadType.PREPEND -> {
                    return MediatorResult.Success(endOfPaginationReached = true)
                }

                LoadType.REFRESH -> 1
                LoadType.APPEND -> {
                    pageKeyEntity = pageKeyLocalDataSource.getPageKeyById(COLLECTION_ID)
                    pageKeyEntity?.let {
                        if (it.lastLoadedPage == it.maxPage) {
                            return MediatorResult.Success(endOfPaginationReached = true)
                        }
                    }
                    (pageKeyEntity?.lastLoadedPage ?: 0) + 1

                }
            }
            val response = collectionRemoteDataSource.getCollections(nextPage)

            database.withTransaction {
                if (loadType == LoadType.REFRESH) {
                    collectionLocalDataSource.deleteAll()
                }

                collectionLocalDataSource.insertCollections(response.data.map(Collection::toEntity))
            }
            val maxPage = ceil((response.total / response.perPage.toDouble())).toInt()

            pageKeyLocalDataSource.insertPageKey(
                PageKeyEntity(
                    collectionId = COLLECTION_ID,
                    lastLoadedPage = nextPage,
                    maxPage = maxPage
                )
            )
            MediatorResult.Success(
                endOfPaginationReached = (response.page * response.perPage >= response.total)
            )
        } catch (e: Exception) {
            MediatorResult.Error(e)
        }
    }

    companion object {
        private const val COLLECTION_ID = "collection"
    }
}

