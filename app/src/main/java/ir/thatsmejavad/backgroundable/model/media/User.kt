package ir.thatsmejavad.backgroundable.model.media

import kotlinx.serialization.Serializable

@Serializable
data class User(
    val id: Int,
    val name: String,
    val url: String
)
