package ir.thatsmejavad.backgroundable.core

import kotlinx.serialization.Serializable

internal sealed interface AppScreens {
    @Serializable
    data object CollectionList : AppScreens

    @Serializable
    data object Search : AppScreens

    @Serializable
    data class MediaList(
        val id: String,
        val title: String
    )

    @Serializable
    data class MediaDetail(
        val id: Int,
        val title: String
    ) : AppScreens

    @Serializable
    data object Settings : AppScreens

    @Serializable
    data object ThemeSetting : AppScreens

    @Serializable
    data object ImageQualitySetting : AppScreens

    @Serializable
    data object AboutUs : AppScreens

    @Serializable
    data object Language : AppScreens
}
