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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

private val darkColorScheme = darkColorScheme(
    primary = Color(0xFFFFB59F),
    onPrimary = Color(0xFF5F1600),
    primaryContainer = Color(0xFF862300),
    onPrimaryContainer = Color(0xFFFFDBD1),
    secondary = Color(0xFFA1C9FF),
    onSecondary = Color(0xFF00325A),
    secondaryContainer = Color(0xFF004880),
    onSecondaryContainer = Color(0xFFD2E4FF),
    tertiary = Color(0xFFD8C58D),
    onTertiary = Color(0xFF3A2F05),
    tertiaryContainer = Color(0xFF524619),
    onTertiaryContainer = Color(0xFFF5E1A7),
    error = Color(0xFFFFB4AB),
    onError = Color(0xFF690005),
    errorContainer = Color(0xFF93000A),
    onErrorContainer = Color(0xFFFFDAD6),
    background = Color(0xFF191C1D),
    onBackground = Color(0xFFE1E3E3),
    inverseOnSurface = Color(0xFF191C1D),
    inverseSurface = Color(0xFFE1E3E3),
    inversePrimary = Color(0xFFAF3000),
    outlineVariant = Color(0xFF53433F),
    surface = Color(0xFF101415),
    onSurface = Color(0xFFC4C7C7),
    surfaceVariant = Color(0xFF53433F),
    onSurfaceVariant = Color(0xFF53433F),
    surfaceTint = Color(0xFFD8C2BC),
    outline = Color(0xFFA08C87),
    scrim = Color(0xFF000000),
)

private val lightColorScheme = lightColorScheme(
    primary = Color(0xFFAF3000),
    onPrimary = Color(0xFFFFFFFF),
    primaryContainer = Color(0xFFFFDBD1),
    onPrimaryContainer = Color(0xFF3A0A00),
    secondary = Color(0xFF0F61A4),
    onSecondary = Color(0xFFFFFFFF),
    secondaryContainer = Color(0xFFD2E4FF),
    onSecondaryContainer = Color(0xFF001C37),
    tertiary = Color(0xFF6B5D2F),
    onTertiary = Color(0xFFFFFFFF),
    tertiaryContainer = Color(0xFFF5E1A7),
    onTertiaryContainer = Color(0xff231B00),
    error = Color(0xffBA1A1A),
    onError = Color(0xFFFFFFFF),
    errorContainer = Color(0xffFFDAD6),
    onErrorContainer = Color(0xFF410002),
    background = Color(0xFFFAFDFD),
    onBackground = Color(0xFF191C1D),
    inverseOnSurface = Color(0xFFEFF1F1),
    inverseSurface = Color(0xFF2E3132),
    inversePrimary = Color(0xFFFFB59F),
    outlineVariant = Color(0xFFD8C2BC),
    surface = Color(0xFFF8FAFA),
    onSurface = Color(0xFF191C1D),
    surfaceVariant = Color(0xFFF5DED7),
    onSurfaceVariant = Color(0xFF53433F),
    surfaceTint = Color(0xFFAF3000),
    outline = Color(0xFF85736E),
    scrim = Color(0xFF000000),
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
