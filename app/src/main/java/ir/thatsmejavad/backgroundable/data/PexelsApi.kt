package ir.thatsmejavad.backgroundable.data

import ir.thatsmejavad.backgroundable.core.MediaType.Photo
import ir.thatsmejavad.backgroundable.model.Collection
import ir.thatsmejavad.backgroundable.model.Media
import ir.thatsmejavad.backgroundable.model.PagedResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PexelsApi {
    @GET("v1/collections/featured")
    suspend fun getCollections(): Response<PagedResponse<Collection>>

    @GET("v1/collections/{collectionId}")
    suspend fun getMediasByCollectionId(
        @Path("collectionId") collectionId: String,
        @Query("type") type: String? = Photo.type
    ): Response<PagedResponse<Media>>

    @GET("v1/photos/{photoId}")
    suspend fun getPhoto(
        @Path("photoId") photoId: String
    ): Response<Media>
}
