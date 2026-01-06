package ir.thatsmejavad.backgroundable.common.ui

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import ir.thatsmejavad.backgroundable.R
import ir.thatsmejavad.backgroundable.core.AppScreens

enum class NavigationBarDestinations(
    @param:DrawableRes val selectedIconRes: Int,
    @param:DrawableRes val unselectedIconRes: Int,
    @param:StringRes val text: Int,
    internal val route: AppScreens,
) {
    HOME(
        selectedIconRes = R.drawable.home_filled,
        unselectedIconRes = R.drawable.home,
        text = R.string.label_home,
        route = AppScreens.CollectionList,
    ),
    SEARCH(
        selectedIconRes = R.drawable.search,
        unselectedIconRes = R.drawable.search,
        text = R.string.label_search,
        route = AppScreens.Search,
    ),
    SETTING(
        selectedIconRes = R.drawable.settings_filled,
        unselectedIconRes = R.drawable.settings,
        text = R.string.label_setting,
        route = AppScreens.Settings,
    )
}
