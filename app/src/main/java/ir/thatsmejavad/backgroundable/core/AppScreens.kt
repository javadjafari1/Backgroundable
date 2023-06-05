package ir.thatsmejavad.backgroundable.core

internal sealed class AppScreens(val route: String) {
    object CollectionList : AppScreens("collection-list")

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
}
