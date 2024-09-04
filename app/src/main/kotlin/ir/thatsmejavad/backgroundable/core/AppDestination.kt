package ir.thatsmejavad.backgroundable.core

import kotlinx.serialization.Serializable

internal sealed interface AppDestination {
    @Serializable
    data object CollectionList : AppDestination

    @Serializable
    data object Search : AppDestination

    @Serializable
    data class MediaList(
        val id: String,
        val title: String,
    ) : AppDestination

    @Serializable
    data class MediaDetail(
        val id: Int,
        val title: String,
    ) : AppDestination

    @Serializable
    data object Settings : AppDestination

    @Serializable
    data object ThemeSetting : AppDestination

    @Serializable
    data object ImageQualitySetting : AppDestination

    @Serializable
    data object AboutUs : AppDestination

    @Serializable
    data object Language : AppDestination
}

internal sealed class AppBottomSheets(val route: String) : AppDestination {

    data object ColumnCountPicker : AppBottomSheets(
        "column-count-picker?items={items}?selectedItem={selectedItem}"
    ) {
        fun createRoute(
            items: String,
            selectedItem: Int
        ): String {
            return "column-count-picker?items=$items?selectedItem=$selectedItem"
        }
    }

    data object DownloadPicker : AppBottomSheets(
        "download-picker?id:{id}"
    ) {
        fun createRoute(id: Int): String {
            return "download-picker?id:$id"
        }
    }

}
