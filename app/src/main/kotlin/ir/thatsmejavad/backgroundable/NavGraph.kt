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
import androidx.navigation.toRoute
import ir.thatsmejavad.backgroundable.core.animatedComposable
import ir.thatsmejavad.backgroundable.screens.settings.imagequalitysetting.ImageQualitySettingScreen
import ir.thatsmejavad.backgroundable.screens.settings.language.LanguageScreen
import ir.thatsmejavad.backgroundable.screens.settings.themesetting.ThemeSettingScreen
import kotlinx.serialization.json.Json

fun NavGraphBuilder.mainNavGraph(navController: NavHostController) {
    animatedComposable<AppScreens.CollectionList> {
        CollectionListScreen(navController = navController)
    }

    animatedComposable<AppScreens.MediaList> { entry ->
        val title = entry.toRoute<AppScreens.MediaList>().title
        MediaListScreen(
            title = title,
            navController = navController
        )
    }

    animatedComposable<AppScreens.MediaDetail> { entry ->

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

    animatedComposable<AppScreens.Search> {
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
    animatedComposable<AppScreens.AboutUs> {
        AboutUsScreen(
            onBackClicked = {
                navController.navigateUp()
            }
        )
    }

    animatedComposable<AppScreens.ImageQualitySetting> {
        ImageQualitySettingScreen(
            navController = navController
        )
    }

    animatedComposable<AppScreens.Settings> {
        SettingsScreen(
            navigateTo = { route ->
                navController.navigate(route)
            }
        )
    }

    animatedComposable<AppScreens.ThemeSetting> {
        ThemeSettingScreen(
            navController = navController
        )
    }
    animatedComposable<AppScreens.Language> {
        LanguageScreen(
            navController = navController
        )
    }
}
