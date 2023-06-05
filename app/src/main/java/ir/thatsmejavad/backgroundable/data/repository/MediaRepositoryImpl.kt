package ir.thatsmejavad.backgroundable.data.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import ir.thatsmejavad.backgroundable.core.Constants.COLLECTIONS_PER_PAGE_ITEM
import ir.thatsmejavad.backgroundable.data.datasource.local.MediaLocalDataSource
import ir.thatsmejavad.backgroundable.data.datasource.local.ResourceLocalDataSource
import ir.thatsmejavad.backgroundable.data.datasource.remote.MediaRemoteDataSource
import ir.thatsmejavad.backgroundable.data.datasource.remote.MediaRemoteMediator
import ir.thatsmejavad.backgroundable.data.db.BackgroundableDatabase
import ir.thatsmejavad.backgroundable.data.db.relation.MediaWithResources
import ir.thatsmejavad.backgroundable.model.media.Media
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MediaRepositoryImpl @Inject constructor(
    private val mediaRemoteDataSource: MediaRemoteDataSource,
    private val mediaLocalDataSource: MediaLocalDataSource,
    private val resourceLocalDataSource: ResourceLocalDataSource,
    private val database: BackgroundableDatabase,
) : MediaRepository {
    override suspend fun getMedia(mediaId: Int): Media {
        return mediaRemoteDataSource.getMedia(mediaId)
    }

    @OptIn(ExperimentalPagingApi::class)
    override fun getMediasByCollectionId(
        collectionId: String,
        shouldFetch: Boolean,
    ): Flow<PagingData<MediaWithResources>> {
        return Pager(
            config = PagingConfig(
                pageSize = COLLECTIONS_PER_PAGE_ITEM,
                initialLoadSize = 1
            ),
            remoteMediator = MediaRemoteMediator(
                database = database,
                mediaLocalDataSource = mediaLocalDataSource,
                mediaRemoteDataSource = mediaRemoteDataSource,
                resourceLocalDataSource = resourceLocalDataSource,
                collectionId = collectionId,
                shouldFetch = shouldFetch,
            ),
            pagingSourceFactory = {
                mediaLocalDataSource.getPagedMedia(collectionId)
            },
        ).flow
    }

    override suspend fun getMediaWithResources(id: Int): MediaWithResources {
        return mediaLocalDataSource.getMediaWithResources(id)
    }
}
