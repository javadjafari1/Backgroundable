package ir.thatsmejavad.backgroundable.core

internal sealed class AppScreens(val route: String) {
    object CollectionList : AppScreens("collection-list")

    object Search : AppScreens("search")

    object MediaList : AppScreens("media-list?id={id}&title={title}") {
        fun createRoute(id: String, title: String): String {
            return "media-list?id=$id&title=$title"
        }
    }

    object MediaDetail : AppScreens("media-detail?id={id}&title={title}") {
        fun createRoute(id: Int, title: String): String {
            return "media-detail?id=$id&title=$title"
        }
    }

    object ColumnCountPicker : AppScreens(
        "column-count-picker?items={items}?selectedItem={selectedItem}"
    ) {
        fun createRoute(items: String, selectedItem: Int): String {
            return "column-count-picker?items=$items?selectedItem=$selectedItem"
        }
    }

    object Settings : AppScreens(
        "settings"
    )

    object ThemeSetting : AppScreens(
        "theme-settings"
    )

    object DownloadPicker : AppScreens(
        "download-picker?id:{id}"
    ) {
        fun createRoute(id: Int): String {
            return "download-picker?id:$id"
        }
    }

    object AboutUs : AppScreens(
        "about-us"
    )
}
