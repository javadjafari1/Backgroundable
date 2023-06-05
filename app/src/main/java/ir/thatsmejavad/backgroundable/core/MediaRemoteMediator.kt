package ir.thatsmejavad.backgroundable.core

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import ir.thatsmejavad.backgroundable.data.datasource.local.MediaLocalDataSource
import ir.thatsmejavad.backgroundable.data.datasource.local.ResourceLocalDataSource
import ir.thatsmejavad.backgroundable.data.datasource.remote.MediaRemoteDataSource
import ir.thatsmejavad.backgroundable.data.db.BackgroundableDatabase
import ir.thatsmejavad.backgroundable.data.db.entity.ResourceEntity
import ir.thatsmejavad.backgroundable.data.db.relation.MediaWithResources

@OptIn(ExperimentalPagingApi::class)
class MediaRemoteMediator(
    private val mediaRemoteDataSource: MediaRemoteDataSource,
    private val mediaLocalDataSource: MediaLocalDataSource,
    private val resourceLocalDataSource: ResourceLocalDataSource,
    private val collectionId: String,
    private val database: BackgroundableDatabase,
    private val shouldFetch: Boolean,
) : RemoteMediator<Int, MediaWithResources>() {

    private var nextPage = 1

    override suspend fun initialize(): InitializeAction {
        return if (shouldFetch) {
            InitializeAction.LAUNCH_INITIAL_REFRESH
        } else {
            InitializeAction.SKIP_INITIAL_REFRESH
        }
    }

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, MediaWithResources>
    ): MediatorResult {
        return try {
            val loadKey = when (loadType) {
                LoadType.PREPEND -> {
                    return MediatorResult.Success(endOfPaginationReached = true)
                }

                LoadType.APPEND, LoadType.REFRESH -> nextPage
            }
            val response = mediaRemoteDataSource.getMediasByCollectionId(
                collectionId = collectionId,
                page = loadKey
            )
            nextPage++
            database.withTransaction {
                if (loadType == LoadType.REFRESH) {
                    mediaLocalDataSource.deleteAll()
                    resourceLocalDataSource.deleteAll()
                }

                val resourceEntities = mutableListOf<ResourceEntity>()
                val mediaEntities = response.data.map { media ->
                    for (value in media.resources) {
                        resourceEntities.add(
                            ResourceEntity(
                                mediaId = media.id,
                                size = ResourceSize.fromString(value.first),
                                url = value.second
                            )
                        )

                    }
                    media.toEntity(MediaType.Photo, collectionId)
                }

                mediaLocalDataSource.insertMedias(mediaEntities)
                resourceLocalDataSource.insertResources(resourceEntities)
            }

            MediatorResult.Success(
                endOfPaginationReached = (response.page * response.perPage > response.total)
            )
        } catch (e: Exception) {
            MediatorResult.Error(e)
        }
    }
}
