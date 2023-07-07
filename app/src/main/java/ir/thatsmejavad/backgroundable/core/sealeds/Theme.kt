package ir.thatsmejavad.backgroundable.core.sealeds

import ir.thatsmejavad.backgroundable.ThemeType

sealed interface Theme {
    object DarkTheme : Theme
    object LightTheme : Theme
    object FollowSystem : Theme
    companion object {
        fun toTheme(theme: ThemeType): Theme {
            return when (theme) {
                ThemeType.THEME_DARK -> DarkTheme
                ThemeType.THEME_LIGHT -> LightTheme
                ThemeType.THEME_FOLLOW_SYSTEM -> FollowSystem
                ThemeType.UNRECOGNIZED -> FollowSystem
            }
        }

        fun fromTheme(theme: Theme): ThemeType {
            return when (theme) {
                DarkTheme -> ThemeType.THEME_DARK
                FollowSystem -> ThemeType.THEME_FOLLOW_SYSTEM
                LightTheme -> ThemeType.THEME_LIGHT
            }
        }
    }
}
