package ir.thatsmejavad.backgroundable.data.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import ir.thatsmejavad.backgroundable.core.Constants.MEDIA_PER_PAGE_ITEM
import ir.thatsmejavad.backgroundable.core.sealeds.MediaType
import ir.thatsmejavad.backgroundable.core.sealeds.ResourceSize
import ir.thatsmejavad.backgroundable.data.datasource.local.MediaLocalDataSource
import ir.thatsmejavad.backgroundable.data.datasource.local.PageKeyLocalDataSource
import ir.thatsmejavad.backgroundable.data.datasource.local.ResourceLocalDataSource
import ir.thatsmejavad.backgroundable.data.datasource.remote.MediaRemoteDataSource
import ir.thatsmejavad.backgroundable.data.datasource.remote.MediaRemoteMediator
import ir.thatsmejavad.backgroundable.data.db.BackgroundableDatabase
import ir.thatsmejavad.backgroundable.data.db.entity.ResourceEntity
import ir.thatsmejavad.backgroundable.data.db.relation.MediaWithResources
import ir.thatsmejavad.backgroundable.model.media.Media
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MediaRepositoryImpl @Inject constructor(
    private val mediaRemoteDataSource: MediaRemoteDataSource,
    private val mediaLocalDataSource: MediaLocalDataSource,
    private val resourceLocalDataSource: ResourceLocalDataSource,
    private val pageKeyLocalDataSource: PageKeyLocalDataSource,
    private val database: BackgroundableDatabase,
) : MediaRepository {

    @OptIn(ExperimentalPagingApi::class)
    override fun getMediasByCollectionId(
        collectionId: String,
    ): Flow<PagingData<MediaWithResources>> {
        return Pager(
            config = PagingConfig(
                pageSize = MEDIA_PER_PAGE_ITEM,
                initialLoadSize = MEDIA_PER_PAGE_ITEM,
            ),
            remoteMediator = MediaRemoteMediator(
                database = database,
                mediaLocalDataSource = mediaLocalDataSource,
                mediaRemoteDataSource = mediaRemoteDataSource,
                resourceLocalDataSource = resourceLocalDataSource,
                pageKeyLocalDataSource = pageKeyLocalDataSource,
                collectionId = collectionId,
            ),
            pagingSourceFactory = {
                mediaLocalDataSource.getPagedMedia(collectionId)
            },
        ).flow
    }

    override suspend fun getMediaWithResources(id: Int): MediaWithResources? {
        val mediaWithResources = mediaLocalDataSource.getMediaWithResources(id)
        if (mediaWithResources != null) return mediaWithResources

        updateMedia(id)
        return mediaLocalDataSource.getMediaWithResources(id)
    }

    override fun searchPhoto(query: String): Flow<PagingData<Media>> {
        return mediaRemoteDataSource.searchPhoto(query)
    }

    private suspend fun updateMedia(mediaId: Int) {
        val media = mediaRemoteDataSource.getMedia(mediaId)
        val resourceEntities = mutableListOf<ResourceEntity>()
        val mediaEntities = media.toEntity(MediaType.Photo, null)

        for (value in media.resources) {
            resourceEntities.add(
                ResourceEntity(
                    mediaId = media.id,
                    size = ResourceSize.fromString(value.first),
                    url = value.second
                )
            )
        }

        mediaLocalDataSource.insertMedias(listOf(mediaEntities))
        resourceLocalDataSource.insertResources(resourceEntities)
    }
}
