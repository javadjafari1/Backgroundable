package ir.thatsmejavad.backgroundable.common.ui

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.ui.graphics.vector.ImageVector
import ir.thatsmejavad.backgroundable.R
import ir.thatsmejavad.backgroundable.core.AppScreens

enum class NavigationBarDestinations(
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    @StringRes val text: Int,
    val route: String,
) {
    HOME(
        selectedIcon = Icons.Filled.Home,
        unselectedIcon = Icons.Outlined.Home,
        text = R.string.label_home,
        route = AppScreens.CollectionList.route,
    ),
    SEARCH(
        selectedIcon = Icons.Filled.Search,
        unselectedIcon = Icons.Outlined.Search,
        text = R.string.label_search,
        route = AppScreens.Search.route,
    ),
    SETTING(
        selectedIcon = Icons.Filled.Settings,
        unselectedIcon = Icons.Outlined.Settings,
        text = R.string.label_setting,
        route = AppScreens.Settings.route,
    )
}
