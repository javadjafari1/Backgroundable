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
import ir.thatsmejavad.backgroundable.screens.imagedetail.ImageDetailScreen
import ir.thatsmejavad.backgroundable.screens.imagedetail.ImageDetailViewModel

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
        val id = entry.arguments?.getString("id")!!
        viewModel.getMedias(id)

        CollectionListScreen(
            title = entry.arguments?.getString("title")!!,
            viewModel = viewModel,
            id = id,
            onMediaClicked = {
                navController.navigate(AppScreens.ImageDetail.createRoute(it))
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
            }
        ),
    ) {
        val viewModel: ImageDetailViewModel = daggerViewModel {
            DaggerImageDetailComponent.builder().build().getViewModel()
        }
        ImageDetailScreen()
    }
}

internal sealed class AppScreens(val route: String) {
    object FeaturedCollections : AppScreens("featured-collections")

    object CollectionList : AppScreens("collection-list?id={id}&title={title}") {
        fun createRoute(id: String, title: String): String {
            return "collection-list?id=$id&title=$title"
        }
    }

    object ImageDetail : AppScreens("image-detail?id={id}") {
        fun createRoute(id: Int): String {
            return "image-detail?id=$id"
        }
    }
}
