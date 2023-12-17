package ir.thatsmejavad.backgroundable.ui

import android.os.Build
import androidx.appcompat.app.AppCompatDelegate
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import ir.thatsmejavad.backgroundable.core.sealeds.ThemeColor
import ir.thatsmejavad.backgroundable.ui.theme.aoDarkColors
import ir.thatsmejavad.backgroundable.ui.theme.aoLightColors
import ir.thatsmejavad.backgroundable.ui.theme.blueVioletDarkColors
import ir.thatsmejavad.backgroundable.ui.theme.blueVioletLightColors
import ir.thatsmejavad.backgroundable.ui.theme.crayolaDarkColors
import ir.thatsmejavad.backgroundable.ui.theme.crayolaLightColors
import ir.thatsmejavad.backgroundable.ui.theme.indigoDarkColors
import ir.thatsmejavad.backgroundable.ui.theme.indigoLightColors
import ir.thatsmejavad.backgroundable.ui.theme.middleRedDarkColors
import ir.thatsmejavad.backgroundable.ui.theme.middleRedLightColors
import ir.thatsmejavad.backgroundable.ui.theme.skobeloffDarkColor
import ir.thatsmejavad.backgroundable.ui.theme.skobeloffLightColors

private val shapes = Shapes(
    extraSmall = RoundedCornerShape(CornerSize(10.dp)),
    small = RoundedCornerShape(CornerSize(16.dp)),
    medium = RoundedCornerShape(CornerSize(24.dp)),
    large = RoundedCornerShape(CornerSize(32.dp)),
    extraLarge = RoundedCornerShape(CornerSize(64.dp)),
)

@Composable
fun BackgroundableTheme(
    themeColor: ThemeColor,
    darkTheme: Boolean,
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> {
            when (themeColor) {
                ThemeColor.Ao -> aoDarkColors
                ThemeColor.BlueViolet -> blueVioletDarkColors
                ThemeColor.MiddleRed -> middleRedDarkColors
                ThemeColor.Skobeloff -> skobeloffDarkColor
                ThemeColor.Crayola -> crayolaDarkColors
                ThemeColor.Indigo -> indigoDarkColors
            }
        }

        else -> {
            when (themeColor) {
                ThemeColor.Ao -> aoLightColors
                ThemeColor.BlueViolet -> blueVioletLightColors
                ThemeColor.MiddleRed -> middleRedLightColors
                ThemeColor.Skobeloff -> skobeloffLightColors
                ThemeColor.Crayola -> crayolaLightColors
                ThemeColor.Indigo -> indigoLightColors
            }
        }
    }

    val isPersianSelected = remember {
        AppCompatDelegate.getApplicationLocales().get(0)?.language == "fa"
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = if (isPersianSelected) {
            PersianTypography
        } else {
            EnglishTypography
        },
        shapes = shapes,
        content = content
    )
}
