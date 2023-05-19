package ir.thatsmejavad.backgroundable.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Collection(
    val id: String,
    val title: String,
    val description: String,

    @SerialName("media_count")
    val mediaCount: Int,

    @SerialName("photos_count")
    val photosCount: Int,

    @SerialName("private")
    val isPrivate: Boolean,

    @SerialName("videos_count")
    val videosCount: Int
)
