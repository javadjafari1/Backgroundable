package ir.thatsmejavad.backgroundable

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import ir.thatsmejavad.backgroundable.screens.collectionlist.CollectionListScreen
import ir.thatsmejavad.backgroundable.screens.featuredCollections.FeaturedCollectionsScreen
import ir.thatsmejavad.backgroundable.screens.imagedetail.ImageDetailScreen

internal fun NavGraphBuilder.mainNavGraph(navController: NavHostController) {
    composable(
        route = AppScreens.FeaturedCollections.route
    ) {
        FeaturedCollectionsScreen(
            onCollectionClicked = { id ->
                navController.navigate(AppScreens.CollectionList.createRoute(id))
            }
        )
    }
    composable(
        route = AppScreens.CollectionList.route,
        arguments = listOf(
            navArgument("id") {
                type = NavType.StringType
                nullable = false
            }
        ),
    ) {
        CollectionListScreen(
            onImageClicked = { id ->
                navController.navigate(AppScreens.ImageDetail.createRoute(id))
            }
        )
    }
    composable(
        route = AppScreens.ImageDetail.route,
        arguments = listOf(
            navArgument("id") {
                type = NavType.StringType
                nullable = false
            }
        ),
    ) {
        ImageDetailScreen()
    }
}

internal sealed class AppScreens(val route: String) {
    object FeaturedCollections : AppScreens("featured-collections")

    object CollectionList : AppScreens("collection-list?id={id}") {
        fun createRoute(id: String): String {
            return "collection-list?id=$id"
        }
    }

    object ImageDetail : AppScreens("image-detail?id={id}") {
        fun createRoute(id: String): String {
            return "image-detail?id=$id"
        }
    }
}
