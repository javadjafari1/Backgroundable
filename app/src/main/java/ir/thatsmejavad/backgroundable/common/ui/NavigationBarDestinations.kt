package ir.thatsmejavad.backgroundable.common.ui

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import ir.thatsmejavad.backgroundable.R

enum class NavigationBarDestinations(
    @DrawableRes val selectedIcon: Int,
    @DrawableRes val unselectedIcon: Int,
    @StringRes val text: Int,
) {
    HOME(
        selectedIcon = R.drawable.ic_home_filled,
        unselectedIcon = R.drawable.ic_home_outline,
        text = R.string.label_home,
    ),
    SEARCH(
        selectedIcon = R.drawable.ic_search,
        unselectedIcon = R.drawable.ic_search,
        text = R.string.label_search,
    )
}
