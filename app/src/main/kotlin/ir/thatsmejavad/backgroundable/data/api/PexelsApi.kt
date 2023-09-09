package ir.thatsmejavad.backgroundable.data.api

import ir.thatsmejavad.backgroundable.core.Constants.COLLECTIONS_PER_PAGE_ITEM
import ir.thatsmejavad.backgroundable.core.Constants.MEDIA_PER_PAGE_ITEM
import ir.thatsmejavad.backgroundable.model.Collection
import ir.thatsmejavad.backgroundable.model.PagedResponse
import ir.thatsmejavad.backgroundable.model.media.Media
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PexelsApi {
    @GET("v1/collections/featured")
    suspend fun getCollections(
        @Query("page") page: Int,
        @Query("per_page") perPage: Int = COLLECTIONS_PER_PAGE_ITEM,
    ): Response<PagedResponse<Collection>>

    @GET("v1/collections/{collectionId}")
    suspend fun getMediasByCollectionId(
        @Path("collectionId") collectionId: String,
        @Query("page") page: Int,
        @Query("type") type: String = "photos",
        @Query("per_page") perPage: Int = MEDIA_PER_PAGE_ITEM,
    ): Response<PagedResponse<Media>>

    @GET("v1/photos/{photoId}")
    suspend fun getPhoto(
        @Path("photoId") photoId: Int
    ): Response<Media>

    @GET("v1/search")
    suspend fun searchPhoto(
        @Query("query") query: String,
        @Query("page") page: Int,
        @Query("per_page") perPage: Int = MEDIA_PER_PAGE_ITEM,
    ): Response<PagedResponse<Media>>
}
