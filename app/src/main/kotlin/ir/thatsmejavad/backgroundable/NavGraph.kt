package ir.thatsmejavad.backgroundable

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import ir.thatsmejavad.backgroundable.core.AppScreens
import ir.thatsmejavad.backgroundable.screens.aboutus.AboutUsScreen
import ir.thatsmejavad.backgroundable.screens.collectionlist.CollectionListScreen
import ir.thatsmejavad.backgroundable.screens.columncountpicker.ColumnCountPicker
import ir.thatsmejavad.backgroundable.screens.downloadpicker.DownloadPickerScreen
import ir.thatsmejavad.backgroundable.screens.mediadetail.MediaDetailScreen
import ir.thatsmejavad.backgroundable.screens.medialist.MediaListScreen
import ir.thatsmejavad.backgroundable.screens.search.SearchScreen
import ir.thatsmejavad.backgroundable.screens.settings.SettingsScreen
import androidx.compose.material.navigation.bottomSheet
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import ir.thatsmejavad.backgroundable.screens.settings.imagequalitysetting.ImageQualitySettingScreen
import ir.thatsmejavad.backgroundable.screens.settings.language.LanguageScreen
import ir.thatsmejavad.backgroundable.screens.settings.themesetting.ThemeSettingScreen
import kotlinx.serialization.json.Json

fun NavGraphBuilder.mainNavGraph(navController: NavHostController) {
    composable<AppScreens.CollectionList> {
        CollectionListScreen(navController = navController)
    }

    composable<AppScreens.MediaList> { entry ->
        val title = entry.toRoute<AppScreens.MediaList>().title
        MediaListScreen(
            title = title,
            navController = navController
        )
    }

    composable<AppScreens.MediaDetail> { entry ->
        val route = entry.toRoute<AppScreens.MediaDetail>()
        MediaDetailScreen(
            title = route.title,
            mediaId = route.id,
            navController = navController
        )
    }

    composable<AppScreens.Search>(
    ) {
        SearchScreen(
            navController = navController
        )
    }

    bottomSheet<AppScreens.ColumnCountPicker> { entry ->
        val items = entry.toRoute<AppScreens.ColumnCountPicker>()
        ColumnCountPicker(
            items = Json.decodeFromString<List<Int>>(items.items),
            selectedItem = items.selectedItem,
            onSelect = { item ->
                navController
                    .previousBackStackEntry
                    ?.savedStateHandle
                    ?.set(
                        key = "selected-item",
                        value = item
                    )
                navController.navigateUp()
            }
        )
    }

    bottomSheet<AppScreens.DownloadPicker> {
        DownloadPickerScreen(navController = navController)
    }
}

fun NavGraphBuilder.settingNavGraph(navController: NavHostController) {
    composable<AppScreens.AboutUs> {
        AboutUsScreen(
            onBackClicked = {
                navController.navigateUp()
            }
        )
    }

    composable<AppScreens.ImageQualitySetting> {
        ImageQualitySettingScreen(
            navController = navController
        )
    }

    composable<AppScreens.Settings> {
        SettingsScreen(
            navigateTo = { route ->
                navController.navigate(route)
            }
        )
    }

    composable<AppScreens.ThemeSetting> {
        ThemeSettingScreen(
            navController = navController
        )
    }
    composable<AppScreens.Language> {
        LanguageScreen(
            navController = navController
        )
    }
}
