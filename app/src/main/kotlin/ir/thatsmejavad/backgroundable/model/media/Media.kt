package ir.thatsmejavad.backgroundable.model.media

import ir.thatsmejavad.backgroundable.core.sealeds.MediaType
import ir.thatsmejavad.backgroundable.data.db.entity.MediaEntity
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Media(
    val id: Int,
    val width: Int,
    val url: String,
    val height: Int,
    val alt: String,
    val type: MediaType?,
    val liked: Boolean?,
    val photographer: String,
    @SerialName("src")
    val resources: Resources,
    val avgColor: String,
    val photographerId: Int,
    val photographerUrl: String,
) {
    fun toEntity(
        collectionId: String?
    ) = MediaEntity(
        id = id,
        width = width,
        height = height,
        url = url,
        alt = alt,
        type = type ?: MediaType.Photo,
        photographer = photographer,
        photographerId = photographerId,
        photographerUrl = photographerUrl,
        avgColor = avgColor,
        collectionId = collectionId
    )
}
