package ir.thatsmejavad.backgroundable

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.navArgument
import ir.thatsmejavad.backgroundable.common.ui.animatedComposable
import ir.thatsmejavad.backgroundable.core.AppBottomSheets
import ir.thatsmejavad.backgroundable.screens.aboutus.AboutUsScreen
import ir.thatsmejavad.backgroundable.screens.collectionlist.CollectionListScreen
import ir.thatsmejavad.backgroundable.screens.columncountpicker.ColumnCountPicker
import ir.thatsmejavad.backgroundable.screens.downloadpicker.DownloadPickerScreen
import ir.thatsmejavad.backgroundable.screens.mediadetail.MediaDetailScreen
import ir.thatsmejavad.backgroundable.screens.medialist.MediaListScreen
import ir.thatsmejavad.backgroundable.screens.search.SearchScreen
import ir.thatsmejavad.backgroundable.screens.settings.SettingsScreen
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.navigation.bottomSheet
import androidx.navigation.toRoute
import ir.thatsmejavad.backgroundable.core.AppDestination
import ir.thatsmejavad.backgroundable.screens.settings.imagequalitysetting.ImageQualitySettingScreen
import ir.thatsmejavad.backgroundable.screens.settings.language.LanguageScreen
import ir.thatsmejavad.backgroundable.screens.settings.themesetting.ThemeSettingScreen
import kotlinx.serialization.json.Json

internal fun NavGraphBuilder.mainNavGraph(navController: NavHostController) {
    animatedComposable<AppDestination.CollectionList> {
        CollectionListScreen(navController = navController)
    }

    animatedComposable<AppDestination.MediaList> { entry ->
        val route = entry.toRoute<AppDestination.MediaList>()
        MediaListScreen(
            title = route.title,
            navController = navController
        )
    }

    animatedComposable<AppDestination.MediaDetail> { entry ->
        val route = entry.toRoute<AppDestination.MediaDetail>()
        MediaDetailScreen(
            title = route.title,
            mediaId = route.id,
            navController = navController
        )
    }

    animatedComposable<AppDestination.Search> {
        SearchScreen(
            navController = navController
        )
    }

    bottomSheet(
        route = AppBottomSheets.ColumnCountPicker.route,
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
        route = AppBottomSheets.DownloadPicker.route,
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

internal fun NavGraphBuilder.settingNavGraph(navController: NavHostController) {
    animatedComposable<AppDestination.AboutUs> {
        AboutUsScreen(
            onBackClicked = {
                navController.navigateUp()
            }
        )
    }

    animatedComposable<AppDestination.ImageQualitySetting> {
        ImageQualitySettingScreen(
            navController = navController
        )
    }

    animatedComposable<AppDestination.Settings> {
        SettingsScreen(
            navigateTo = { route ->
                navController.navigate(route)
            }
        )
    }

    animatedComposable<AppDestination.ThemeSetting> {
        ThemeSettingScreen(
            navController = navController
        )
    }

    animatedComposable<AppDestination.Language> {
        LanguageScreen(
            navController = navController
        )
    }
}
