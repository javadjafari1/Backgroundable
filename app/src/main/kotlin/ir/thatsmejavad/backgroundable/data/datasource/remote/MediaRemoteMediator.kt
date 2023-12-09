package ir.thatsmejavad.backgroundable.data.datasource.remote

import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import ir.thatsmejavad.backgroundable.core.Constants.MEDIA_REFRESH_TIME_IN_HOUR
import ir.thatsmejavad.backgroundable.core.convertToRelativePath
import ir.thatsmejavad.backgroundable.core.sealeds.ResourceSize
import ir.thatsmejavad.backgroundable.data.datasource.local.MediaLocalDataSource
import ir.thatsmejavad.backgroundable.data.datasource.local.PageKeyLocalDataSource
import ir.thatsmejavad.backgroundable.data.datasource.local.ResourceLocalDataSource
import ir.thatsmejavad.backgroundable.data.db.BackgroundableDatabase
import ir.thatsmejavad.backgroundable.data.db.entity.PageKeyEntity
import ir.thatsmejavad.backgroundable.data.db.entity.ResourceEntity
import ir.thatsmejavad.backgroundable.data.db.relation.MediaWithResources
import java.util.concurrent.TimeUnit
import kotlin.math.ceil

class MediaRemoteMediator(
    private val mediaRemoteDataSource: MediaRemoteDataSource,
    private val mediaLocalDataSource: MediaLocalDataSource,
    private val resourceLocalDataSource: ResourceLocalDataSource,
    private val pageKeyLocalDataSource: PageKeyLocalDataSource,
    private val collectionId: String,
    private val database: BackgroundableDatabase,
) : RemoteMediator<Int, MediaWithResources>() {

    private var pageKeyEntity: PageKeyEntity? = null

    override suspend fun initialize(): InitializeAction {
        pageKeyEntity = pageKeyLocalDataSource.getPageKeyById(collectionId)

        val lastUpdateTime = pageKeyEntity?.timestamp ?: -1
        val elapsedHours =
            TimeUnit.MILLISECONDS.toHours(System.currentTimeMillis() - lastUpdateTime)

        return if (lastUpdateTime == -1L || elapsedHours > MEDIA_REFRESH_TIME_IN_HOUR) {
            InitializeAction.LAUNCH_INITIAL_REFRESH
        } else {
            InitializeAction.SKIP_INITIAL_REFRESH
        }
    }

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, MediaWithResources>
    ): MediatorResult {
        return runCatching {
            val nextPage = when (loadType) {
                LoadType.PREPEND -> return MediatorResult.Success(endOfPaginationReached = true)
                LoadType.REFRESH -> 1
                LoadType.APPEND -> {
                    pageKeyEntity = pageKeyLocalDataSource.getPageKeyById(collectionId)
                    pageKeyEntity?.let {
                        if (it.lastLoadedPage == it.maxPage) {
                            return MediatorResult.Success(endOfPaginationReached = true)
                        }
                    }
                    (pageKeyEntity?.lastLoadedPage ?: 0) + 1
                }
            }

            val response = mediaRemoteDataSource.getMediasByCollectionId(
                collectionId = collectionId,
                page = nextPage
            )
            database.withTransaction {
                if (loadType == LoadType.REFRESH) {
                    pageKeyLocalDataSource.deletePageKeyById(collectionId)
                }

                val resourceEntities = mutableListOf<ResourceEntity>()
                val mediaEntities = response.data.map { media ->
                    for (value in media.resources) {
                        resourceEntities.add(
                            ResourceEntity(
                                mediaId = media.id,
                                size = ResourceSize.fromString(value.first),
                                url = value.second.convertToRelativePath() ?: ""
                            )
                        )
                    }
                    media.toEntity(collectionId)
                }

                mediaLocalDataSource.insertMedias(mediaEntities)
                resourceLocalDataSource.insertResources(resourceEntities)
                val maxPage = ceil((response.total / response.perPage.toDouble()))
                pageKeyLocalDataSource.insertPageKey(
                    PageKeyEntity(
                        collectionId = collectionId,
                        lastLoadedPage = nextPage,
                        maxPage = maxPage.toInt()
                    )
                )
            }

            MediatorResult.Success(
                endOfPaginationReached = (response.page * response.perPage >= response.total)
            )
        }.getOrElse {
            MediatorResult.Error(it)
        }
    }
}
