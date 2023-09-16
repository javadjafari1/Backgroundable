package ir.thatsmejavad.backgroundable.ui

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Shapes
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.unit.dp
import androidx.core.view.WindowCompat
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
    extraSmall = ShapeDefaults.ExtraSmall.copy(CornerSize(10.dp)),
    small = ShapeDefaults.ExtraSmall.copy(CornerSize(16.dp)),
    medium = ShapeDefaults.ExtraSmall.copy(CornerSize(24.dp)),
    large = ShapeDefaults.ExtraSmall.copy(CornerSize(32.dp)),
    extraLarge = ShapeDefaults.ExtraSmall.copy(CornerSize(64.dp)),
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
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colorScheme.background.copy(alpha = 0.1f).toArgb()
            if (!darkTheme && Build.VERSION.SDK_INT < Build.VERSION_CODES.O_MR1) {
                window.navigationBarColor = Color.Black.copy(alpha = 0.3f).toArgb()
            } else {
                window.navigationBarColor = colorScheme.background.copy(alpha = 0.1f).toArgb()
            }
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = !darkTheme
            WindowCompat.getInsetsController(window, view).isAppearanceLightNavigationBars =
                !darkTheme
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        shapes = shapes,
        content = content
    )
}
