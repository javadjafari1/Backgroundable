package ir.thatsmejavad.backgroundable.ui.theme

import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.ui.graphics.Color

private val primary10 = Color(color = 0xFF00201F)
private val primary20 = Color(color = 0xFF003736)
private val primary30 = Color(color = 0xFF00504F)
private val primary40 = Color(color = 0xFF006A69)
private val primary80 = Color(color = 0xFF4DDAD8)
private val primary90 = Color(color = 0xFF6FF7F5)

private val secondary10 = Color(color = 0xFF051F1F)
private val secondary20 = Color(color = 0xFF1B3534)
private val secondary30 = Color(color = 0xFF324B4A)
private val secondary40 = Color(color = 0xFF4A6362)
private val secondary80 = Color(color = 0xFFB0CCCB)
private val secondary90 = Color(color = 0xFFCCE8E7)

private val tertiary10 = Color(color = 0xFF031C35)
private val tertiary20 = Color(color = 0xFF1B324B)
private val tertiary30 = Color(color = 0xFF334863)
private val tertiary40 = Color(color = 0xFF4B607C)
private val tertiary80 = Color(color = 0xFFB2C8E8)
private val tertiary90 = Color(color = 0xFFD2E4FF)

private val error10 = Color(color = 0xFF410002)
private val error20 = Color(color = 0xFF690005)
private val error30 = Color(color = 0xFF93000A)
private val error40 = Color(color = 0xFFBA1A1A)
private val error80 = Color(color = 0xFFFFB4AB)
private val error90 = Color(color = 0xFFFFDAD6)

private val neutral04 = Color(color = 0xFF0B0F0F)
private val neutral06 = Color(color = 0xFF101414)
private val neutral10 = Color(color = 0xFF191C1C)
private val neutral12 = Color(color = 0xFF1D2020)
private val neutral17 = Color(color = 0xFF272B2A)
private val neutral20 = Color(color = 0xFF2D3131)
private val neutral22 = Color(color = 0xFF323535)
private val neutral24 = Color(color = 0xFF363A3A)
private val neutral87 = Color(color = 0xFFD8DADA)
private val neutral90 = Color(color = 0xFFE0E3E2)
private val neutral92 = Color(color = 0xFFE6E9E8)
private val neutral94 = Color(color = 0xFFECEEED)
private val neutral95 = Color(color = 0xFFEFF1F0)
private val neutral96 = Color(color = 0xFFF2F4F3)
private val neutral98 = Color(color = 0xFFF7FAF9)
private val neutral99 = Color(color = 0xFFFAFDFC)

private val neutralVariant30 = Color(color = 0xFF3F4948)
private val neutralVariant60 = Color(color = 0xFF889392)
private val neutralVariant80 = Color(color = 0xFFBEC9C8)
private val neutralVariant90 = Color(color = 0xFFDAE5E3)
private val neutralVariant95 = Color(color = 0xFF6F7978)

val skobeloffLightColors = lightColorScheme(
    primary = primary40,
    onPrimary = Color.White,
    primaryContainer = primary90,
    onPrimaryContainer = primary10,
    secondary = secondary40,
    onSecondary = Color.White,
    secondaryContainer = secondary90,
    onSecondaryContainer = secondary10,
    tertiary = tertiary40,
    onTertiary = Color.White,
    tertiaryContainer = tertiary90,
    onTertiaryContainer = tertiary10,
    error = error40,
    errorContainer = error90,
    onError = Color.White,
    onErrorContainer = error10,
    background = neutral99,
    onBackground = neutral10,
    surface = neutral99,
    onSurface = neutral10,
    surfaceVariant = neutralVariant90,
    onSurfaceVariant = neutralVariant30,
    outline = neutralVariant95,
    inverseOnSurface = neutral95,
    inverseSurface = neutral20,
    inversePrimary = primary80,
    surfaceTint = primary40,
    outlineVariant = neutralVariant80,
    scrim = Color.Black,
    surfaceContainerHighest = neutral90,
    surfaceContainerHigh = neutral92,
    surfaceContainer = neutral94,
    surfaceContainerLow = neutral96,
    surfaceContainerLowest = Color.White,
    surfaceDim = neutral87,
    surfaceBright = neutral98,
)

val skobeloffDarkColor = darkColorScheme(
    primary = primary80,
    onPrimary = primary20,
    primaryContainer = primary30,
    onPrimaryContainer = primary90,
    secondary = secondary80,
    onSecondary = secondary20,
    secondaryContainer = secondary30,
    onSecondaryContainer = secondary90,
    tertiary = tertiary80,
    onTertiary = tertiary20,
    tertiaryContainer = tertiary30,
    onTertiaryContainer = tertiary90,
    error = error80,
    errorContainer = error30,
    onError = error20,
    onErrorContainer = error90,
    background = neutral10,
    onBackground = neutral90,
    surface = neutral10,
    onSurface = neutral90,
    surfaceVariant = neutralVariant30,
    onSurfaceVariant = neutralVariant80,
    outline = neutralVariant60,
    inverseOnSurface = neutral10,
    inverseSurface = neutral90,
    inversePrimary = primary40,
    surfaceTint = primary80,
    outlineVariant = neutralVariant30,
    scrim = Color.Black,
    surfaceContainerHighest = neutral22,
    surfaceContainerHigh = neutral17,
    surfaceContainer = neutral12,
    surfaceContainerLow = neutral10,
    surfaceContainerLowest = neutral04,
    surfaceDim = neutral06,
    surfaceBright = neutral24,
)
