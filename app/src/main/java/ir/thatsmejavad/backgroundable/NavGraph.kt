package ir.thatsmejavad.backgroundable

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import ir.thatsmejavad.backgroundable.core.daggerViewModel
import ir.thatsmejavad.backgroundable.di.components.DaggerCollectionListComponent
import ir.thatsmejavad.backgroundable.di.components.DaggerFeaturedCollectionsComponent
import ir.thatsmejavad.backgroundable.di.components.DaggerImageDetailComponent
import ir.thatsmejavad.backgroundable.screens.collectionlist.CollectionListScreen
import ir.thatsmejavad.backgroundable.screens.collectionlist.CollectionListViewModel
import ir.thatsmejavad.backgroundable.screens.featuredcollections.FeaturedCollectionsScreen
import ir.thatsmejavad.backgroundable.screens.featuredcollections.FeaturedCollectionsViewModel
import ir.thatsmejavad.backgroundable.screens.mediadetail.MediaDetailScreen
import ir.thatsmejavad.backgroundable.screens.mediadetail.MediaDetailViewModel

internal fun NavGraphBuilder.mainNavGraph(navController: NavHostController) {
    composable(
        route = AppScreens.FeaturedCollections.route
    ) {
        val viewModel: FeaturedCollectionsViewModel = daggerViewModel {
            DaggerFeaturedCollectionsComponent.builder().build().getViewModel()
        }
        FeaturedCollectionsScreen(
            viewModel = viewModel,
            onCollectionClicked = { id, title ->
                navController.navigate(
                    AppScreens.CollectionList.createRoute(
                        id = id,
                        title = title,
                    )
                )
            }
        )
    }
    composable(
        route = AppScreens.CollectionList.route,
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
        val viewModel: CollectionListViewModel = daggerViewModel {
            DaggerCollectionListComponent.builder().build().getViewModel()
        }
        val collectionId = entry.arguments?.getString("id")
            ?: throw IllegalArgumentException("collectionId should not be null!")

        val title = entry.arguments?.getString("title")
            ?: throw IllegalArgumentException("title should not be null!")

        viewModel.getMedias(collectionId)

        CollectionListScreen(
            title = title,
            viewModel = viewModel,
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
        val viewModel: MediaDetailViewModel = daggerViewModel {
            DaggerImageDetailComponent.builder().build().getViewModel()
        }
        val mediaId = entry.arguments?.getInt("id")
            ?: throw IllegalArgumentException("mediaId should not be null")

        val title = entry.arguments?.getString("title")
            ?: throw IllegalArgumentException("title should not be null")

        viewModel.getMedia(mediaId)

        MediaDetailScreen(
            mediaId = mediaId,
            title = title,
            viewModel = viewModel,
            onBackClicked = { navController.navigateUp() }
        )
    }
}

internal sealed class AppScreens(val route: String) {
    object FeaturedCollections : AppScreens("featured-collections")

    object CollectionList : AppScreens("collection-list?id={id}&title={title}") {
        fun createRoute(id: String, title: String): String {
            return "collection-list?id=$id&title=$title"
        }
    }

    object ImageDetail : AppScreens("image-detail?id={id}&title={title}") {
        fun createRoute(id: Int, title: String): String {
            return "image-detail?id=$id&title=$title"
        }
    }
}
