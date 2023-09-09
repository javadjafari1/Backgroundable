package ir.thatsmejavad.backgroundable.core

internal sealed class AppScreens(val route: String) {
    data object CollectionList : AppScreens("collection-list")

    data object Search : AppScreens("search")

    data object MediaList : AppScreens("media-list?id={id}&title={title}") {
        fun createRoute(id: String, title: String): String {
            return "media-list?id=$id&title=$title"
        }
    }

    data object MediaDetail : AppScreens("media-detail?id={id}&title={title}") {
        fun createRoute(id: Int, title: String): String {
            return "media-detail?id=$id&title=$title"
        }
    }

    data object ColumnCountPicker : AppScreens(
        "column-count-picker?items={items}?selectedItem={selectedItem}"
    ) {
        fun createRoute(items: String, selectedItem: Int): String {
            return "column-count-picker?items=$items?selectedItem=$selectedItem"
        }
    }

    data object Settings : AppScreens(
        "settings"
    )

    data object ThemeSetting : AppScreens(
        "theme-settings"
    )

    data object DownloadPicker : AppScreens(
        "download-picker?id:{id}"
    ) {
        fun createRoute(id: Int): String {
            return "download-picker?id:$id"
        }
    }

    data object AboutUs : AppScreens(
        "about-us"
    )
}
