package ir.thatsmejavad.backgroundable.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Media(
    val id: Int,
    val height: Int,
    val width: Int,
    val url: String,
    val photographer: String,
    val alt: String,
    val liked: Boolean,
    val resources: Resources,
    val type: String = "Photo",

    @SerialName("avg_color")
    val avgColor: String,

    @SerialName("photographer_id")
    val photographerId: Int,

    @SerialName("photographer_url")
    val photographerUrl: String,
)
