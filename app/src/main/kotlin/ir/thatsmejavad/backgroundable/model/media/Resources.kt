package ir.thatsmejavad.backgroundable.model.media

import kotlinx.serialization.Serializable

@Serializable
data class Resources(
    val landscape: String,
    val large: String,
    val large2x: String,
    val medium: String,
    val original: String,
    val portrait: String,
    val small: String,
    val tiny: String,
) : Iterable<Pair<String, String>> {
    override fun iterator(): Iterator<Pair<String, String>> {
        return listOf(
            (Resources::landscape.name to landscape),
            (Resources::large.name to large),
            (Resources::large2x.name to large2x),
            (Resources::medium.name to medium),
            (Resources::original.name to original),
            (Resources::portrait.name to portrait),
            (Resources::small.name to small),
            (Resources::tiny.name to tiny)
        ).iterator()
    }
}
