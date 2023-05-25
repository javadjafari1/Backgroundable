package ir.thatsmejavad.backgroundable.model.media

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Media(
    val id: Int,
    val width: Int,
    val user: User?,
    val url: String,
    val height: Int,
    val alt: String?,
    val type: String,
    val image: String?,
    val liked: Boolean?,
    val duration: Int?,
    val photographer: String?,
    val tags: List<String>?,
    @SerialName("src")
    val resources: Resources? = null,
    val fullRes: String?,
    val avgColor: String?,
    val photographerId: Int?,
    val photographerUrl: String?,
    val videoFiles: List<VideoFile>?,
    val videoPictures: List<VideoPicture>?,
)