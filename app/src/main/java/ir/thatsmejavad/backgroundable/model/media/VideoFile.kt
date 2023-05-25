package ir.thatsmejavad.backgroundable.model.media

import kotlinx.serialization.Serializable

@Serializable
data class VideoFile(
    val fileType: String,
    val fps: Double?,
    val height: Int,
    val id: Int,
    val link: String,
    val quality: String,
    val width: Int
)
