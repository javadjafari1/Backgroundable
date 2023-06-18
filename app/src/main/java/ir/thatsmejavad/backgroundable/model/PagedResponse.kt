package ir.thatsmejavad.backgroundable.model

import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonNames

@OptIn(ExperimentalSerializationApi::class)
@Serializable
data class PagedResponse<T>(
    @JsonNames("collections", "media", "photos")
    val data: List<T>,
    val page: Int,
    val perPage: Int,
    @SerialName("total_results")
    val total: Int,
)
