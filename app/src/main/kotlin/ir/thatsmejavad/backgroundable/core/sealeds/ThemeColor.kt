package ir.thatsmejavad.backgroundable.core.sealeds

import ir.thatsmejavad.backgroundable.ThemeName

sealed interface ThemeColor {
    data object Ao : ThemeColor

    data object Skobeloff : ThemeColor

    data object BlueViolet : ThemeColor

    data object MiddleRed : ThemeColor

    data object Crayola : ThemeColor

    data object Indigo : ThemeColor

    companion object {
        val items = listOf(Skobeloff, Ao, BlueViolet, MiddleRed, Crayola, Indigo)

        fun fromThemeColor(color: ThemeColor): ThemeName {
            return when (color) {
                Ao -> ThemeName.THEME_AO
                BlueViolet -> ThemeName.THEME_BLUE_VIOLET
                MiddleRed -> ThemeName.THEME_MIDDLE_RED
                Skobeloff -> ThemeName.THEME_SKOBELOFF
                Crayola -> ThemeName.THEME_CRAYOLA
                Indigo -> ThemeName.THEME_INDIGO
            }
        }

        fun toThemeColor(name: ThemeName): ThemeColor {
            return when (name) {
                ThemeName.THEME_SKOBELOFF -> Skobeloff
                ThemeName.THEME_AO -> Ao
                ThemeName.THEME_BLUE_VIOLET -> BlueViolet
                ThemeName.THEME_MIDDLE_RED -> MiddleRed
                ThemeName.THEME_CRAYOLA -> Crayola
                ThemeName.UNRECOGNIZED -> Skobeloff
                ThemeName.THEME_INDIGO -> Indigo
            }
        }
    }
}
