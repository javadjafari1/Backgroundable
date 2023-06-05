package ir.thatsmejavad.backgroundable

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import ir.thatsmejavad.backgroundable.core.viewmodel.daggerViewModel
import ir.thatsmejavad.backgroundable.screens.featuredcollections.FeaturedCollectionsScreen
import ir.thatsmejavad.backgroundable.screens.mediadetail.MediaDetailScreen
import ir.thatsmejavad.backgroundable.screens.medialist.MediaListScreen

internal fun NavGraphBuilder.mainNavGraph(navController: NavHostController) {
    composable(
        route = AppScreens.FeaturedCollections.route
    ) {
        FeaturedCollectionsScreen(
            viewModel = daggerViewModel(),
            onCollectionClicked = { id, title ->
                navController.navigate(
                    AppScreens.MediaList.createRoute(
                        id = id,
                        title = title,
                    )
                )
            }
        )
    }

    composable(
        route = AppScreens.MediaList.route,
        arguments = listOf(
            navArgument("id") {
                type = NavType.StringType
                nullable = false
            },
            navArgument("title") {
                type = NavType.StringType
                nullable = false
            },
        ),
    ) { entry ->

        val collectionId = checkNotNull(entry.arguments?.getString("id")) {
            "collectionId should not be null!"
        }

        val title = checkNotNull(entry.arguments?.getString("title")) {
            "title should not be null!"
        }

        MediaListScreen(
            title = title,
            viewModel = daggerViewModel(),
            id = collectionId,
            onMediaClicked = { id, alt ->
                navController.navigate(AppScreens.ImageDetail.createRoute(id, alt))
            },
            onBackClicked = { navController.navigateUp() }
        )
    }

    composable(
        route = AppScreens.ImageDetail.route,
        arguments = listOf(
            navArgument("id") {
                type = NavType.IntType
                nullable = false
            },
            navArgument("title") {
                type = NavType.StringType
                nullable = false
            }
        ),
    ) { entry ->

        val mediaId = checkNotNull(entry.arguments?.getInt("id")) {
            "mediaId should not be null"
        }

        val title = checkNotNull(entry.arguments?.getString("title")) {
            "title should not be null"
        }

        MediaDetailScreen(
            mediaId = mediaId,
            title = title,
            viewModel = daggerViewModel(),
            onBackClicked = { navController.navigateUp() }
        )
    }
}

internal sealed class AppScreens(val route: String) {
    object FeaturedCollections : AppScreens("featured-collections")

    object MediaList : AppScreens("media-list?id={id}&title={title}") {
        fun createRoute(id: String, title: String): String {
            return "media-list?id=$id&title=$title"
        }
    }

    object ImageDetail : AppScreens("image-detail?id={id}&title={title}") {
        fun createRoute(id: Int, title: String): String {
            return "image-detail?id=$id&title=$title"
        }
    }
}
