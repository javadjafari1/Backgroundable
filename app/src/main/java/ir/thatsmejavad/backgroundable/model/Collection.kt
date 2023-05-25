package ir.thatsmejavad.backgroundable.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Collection(
    val id: String,
    val title: String,
    val description: String? = null,
    val mediaCount: Int,
    val photosCount: Int,

    @SerialName("private")
    val isPrivate: Boolean,
    val videosCount: Int,
)
