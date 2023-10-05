package ir.thatsmejavad.backgroundable

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.google.accompanist.navigation.material.bottomSheet
import ir.thatsmejavad.backgroundable.common.ui.animatedComposable
import ir.thatsmejavad.backgroundable.core.AppScreens
import ir.thatsmejavad.backgroundable.screens.aboutus.AboutUsScreen
import ir.thatsmejavad.backgroundable.screens.collectionlist.CollectionListScreen
import ir.thatsmejavad.backgroundable.screens.columncountpicker.ColumnCountPicker
import ir.thatsmejavad.backgroundable.screens.downloadpicker.DownloadPickerScreen
import ir.thatsmejavad.backgroundable.screens.mediadetail.MediaDetailScreen
import ir.thatsmejavad.backgroundable.screens.medialist.MediaListScreen
import ir.thatsmejavad.backgroundable.screens.search.SearchScreen
import ir.thatsmejavad.backgroundable.screens.settings.SettingsScreen
import ir.thatsmejavad.backgroundable.screens.settings.imagequalitysetting.ImageQualitySettingScreen
import ir.thatsmejavad.backgroundable.screens.settings.themesetting.ThemeSettingScreen
import kotlinx.serialization.json.Json

fun NavGraphBuilder.mainNavGraph(navController: NavHostController) {
    animatedComposable(
        route = AppScreens.CollectionList.route
    ) {
        CollectionListScreen(navController = navController)
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
            navController = navController
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
            title = title,
            mediaId = mediaId,
            navController = navController
        )
    }

    animatedComposable(
        route = AppScreens.Search.route,
    ) {
        SearchScreen(
            navController = navController
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

    bottomSheet(
        route = AppScreens.DownloadPicker.route,
        arguments = listOf(
            navArgument("id") {
                type = NavType.IntType
                nullable = false
            }
        )
    ) {
        DownloadPickerScreen(navController = navController)
    }
}

fun NavGraphBuilder.settingNavGraph(navController: NavHostController) {
    animatedComposable(
        route = AppScreens.AboutUs.route
    ) {
        AboutUsScreen(
            onBackClicked = {
                navController.navigateUp()
            }
        )
    }

    animatedComposable(
        route = AppScreens.ImageQualitySetting.route
    ) {
        ImageQualitySettingScreen(
            navController = navController
        )
    }

    animatedComposable(
        route = AppScreens.Settings.route,
    ) {
        SettingsScreen(
            navigateTo = { route ->
                navController.navigate(route)
            }
        )
    }

    animatedComposable(
        route = AppScreens.ThemeSetting.route,
    ) {
        ThemeSettingScreen(
            navController = navController
        )
    }
}
