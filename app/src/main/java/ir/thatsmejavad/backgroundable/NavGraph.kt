package ir.thatsmejavad.backgroundable

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.google.accompanist.navigation.material.ExperimentalMaterialNavigationApi
import com.google.accompanist.navigation.material.bottomSheet
import ir.thatsmejavad.backgroundable.common.ui.ObserveArgument
import ir.thatsmejavad.backgroundable.common.ui.animatedComposable
import ir.thatsmejavad.backgroundable.core.AppScreens
import ir.thatsmejavad.backgroundable.core.viewmodel.daggerViewModel
import ir.thatsmejavad.backgroundable.screens.collectionlist.CollectionListScreen
import ir.thatsmejavad.backgroundable.screens.collectionlist.CollectionListViewModel
import ir.thatsmejavad.backgroundable.screens.columncountpicker.ColumnCountPicker
import ir.thatsmejavad.backgroundable.screens.mediadetail.MediaDetailScreen
import ir.thatsmejavad.backgroundable.screens.medialist.MediaListScreen
import ir.thatsmejavad.backgroundable.screens.search.SearchScreen
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

@OptIn(ExperimentalAnimationApi::class, ExperimentalMaterialNavigationApi::class)
internal fun NavGraphBuilder.mainNavGraph(navController: NavHostController) {
    animatedComposable(
        route = AppScreens.CollectionList.route
    ) {
        val viewModel = daggerViewModel<CollectionListViewModel>()

        navController.ObserveArgument<Int>(key = "selected-item") {
            viewModel.setColumnCount(it)
        }

        CollectionListScreen(
            viewModel = viewModel,
            onCollectionClicked = { id, title ->
                navController.navigate(
                    AppScreens.MediaList.createRoute(
                        id = id,
                        title = title,
                    )
                )
            },
            openColumnCountPicker = { selectedItem ->
                // Todo fix this. this action will take place every time.
                val items = listOf(1, 2, 3)
                val stringItem = Json.encodeToString(items)
                navController.navigate(
                    AppScreens.ColumnCountPicker.createRoute(
                        items = stringItem,
                        selectedItem = selectedItem
                    )
                )
            }
        )
    }

    animatedComposable(
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

        val title = checkNotNull(entry.arguments?.getString("title")) {
            "title should not be null!"
        }

        MediaListScreen(
            title = title,
            viewModel = daggerViewModel(),
            onMediaClicked = { id, alt ->
                navController.navigate(AppScreens.MediaDetail.createRoute(id, alt))
            },
            onBackClicked = { navController.navigateUp() }
        )
    }

    animatedComposable(
        route = AppScreens.MediaDetail.route,
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

    animatedComposable(
        route = AppScreens.Search.route,
    ) {
        SearchScreen(
            viewModel = daggerViewModel(),
            onMediaClicked = { id, alt ->
                navController.navigate(AppScreens.MediaDetail.createRoute(id, alt))
            },
        )
    }

    bottomSheet(
        route = AppScreens.ColumnCountPicker.route,
        arguments = listOf(
            navArgument("items") {
                type = NavType.StringType
                nullable = false
            },
            navArgument("selectedItem") {
                type = NavType.IntType
                nullable = false
            }
        )
    ) { entry ->
        val itemString = checkNotNull(entry.arguments?.getString("items")) {
            "items should not be null"
        }
        val selectedItem = checkNotNull(entry.arguments?.getInt("selectedItem")) {
            "selectedItem should not be null"
        }

        val items = Json.decodeFromString<List<Int>>(itemString)
        ColumnCountPicker(
            items = items,
            selectedItem = selectedItem,
            onSelect = { item ->
                navController
                    .previousBackStackEntry
                    ?.savedStateHandle
                    ?.set("selected-item", item)
                navController.navigateUp()
            }
        )
    }
}
