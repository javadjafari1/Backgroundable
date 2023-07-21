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

enum class NavigationBarDestinations(
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    @StringRes val text: Int,
) {
    HOME(
        selectedIcon = Icons.Filled.Home,
        unselectedIcon = Icons.Outlined.Home,
        text = R.string.label_home,
    ),
    SEARCH(
        selectedIcon = Icons.Filled.Search,
        unselectedIcon = Icons.Outlined.Search,
        text = R.string.label_search,
    ),
    SETTING(
        selectedIcon = Icons.Filled.Settings,
        unselectedIcon = Icons.Outlined.Settings,
        text = R.string.label_setting,
    )
}
