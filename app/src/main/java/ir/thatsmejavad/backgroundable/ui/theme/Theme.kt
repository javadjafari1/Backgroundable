package ir.thatsmejavad.backgroundable.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

private val darkColorScheme = darkColorScheme(
    primary = primary80,
    onPrimary = primary20,
    primaryContainer = primary30,
    onPrimaryContainer = primary90,
    secondary = secondary80,
    onSecondary = secondary20,
    secondaryContainer = secondary30,
    onSecondaryContainer = secondary98,
    tertiary = tertiary80,
    onTertiary = tertiary20,
    tertiaryContainer = tertiary30,
    onTertiaryContainer = tertiary90,
    error = error80,
    onError = error20,
    errorContainer = error30,
    onErrorContainer = error90,
    background = neutral10,
    onBackground = neutral90,
    inverseOnSurface = neutral10,
    inverseSurface = neutral90,
    inversePrimary = primary40,
    outlineVariant = neutralVariant30,
    surface = neutral6,
    onSurface = neutral80,
    surfaceVariant = neutralVariant30,
    onSurfaceVariant = neutralVariant30,
    surfaceTint = neutralVariant80,
    outline = neutralVariant60,
    scrim = black,
)
private val lightColorScheme = lightColorScheme(
    primary = primary40,
    onPrimary = white,
    primaryContainer = primary90,
    onPrimaryContainer = primary10,
    secondary = secondary40,
    onSecondary = white,
    secondaryContainer = secondary98,
    onSecondaryContainer = secondary10,
    tertiary = tertiary40,
    onTertiary = white,
    tertiaryContainer = tertiary90,
    onTertiaryContainer = tertiary10,
    error = error40,
    onError = white,
    errorContainer = error95,
    onErrorContainer = error10,
    background = neutral99,
    onBackground = neutral10,
    inverseOnSurface = neutral95,
    inverseSurface = neutral20,
    inversePrimary = primary80,
    outlineVariant = neutralVariant80,
    surface = neutral98,
    onSurface = neutral10,
    surfaceVariant = neutralVariant90,
    onSurfaceVariant = neutralVariant30,
    surfaceTint = primary40,
    outline = neutralVariant50,
    scrim = black,
)

@Composable
fun BackgroundableTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> darkColorScheme
        else -> lightColorScheme
    }
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colorScheme.background.toArgb()
            window.navigationBarColor = colorScheme.background.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = !darkTheme
            WindowCompat.getInsetsController(window, view).isAppearanceLightNavigationBars =
                !darkTheme
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
