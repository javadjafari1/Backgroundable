package ir.thatsmejavad.backgroundable.model.media

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Media(
    val id: Int,
    val width: Int,
    val url: String,
    val height: Int,
    val alt: String,
    val liked: Boolean?,
    val photographer: String,
    @SerialName("src")
    val resources: Resources,
    val avgColor: String,
    val photographerId: Int,
    val photographerUrl: String,
)
