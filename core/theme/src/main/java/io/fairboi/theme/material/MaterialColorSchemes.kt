package io.fairboi.theme.material

import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import io.fairboi.theme.Black
import io.fairboi.theme.DarkBlue
import io.fairboi.theme.DarkGray
import io.fairboi.theme.DarkGrayLight
import io.fairboi.theme.DarkGreen
import io.fairboi.theme.DarkOverlay
import io.fairboi.theme.DarkPrimaryBack
import io.fairboi.theme.DarkPrimaryLabel
import io.fairboi.theme.DarkRed
import io.fairboi.theme.DarkSecondaryBack
import io.fairboi.theme.DarkTertiaryLabel
import io.fairboi.theme.LightBlue
import io.fairboi.theme.LightGray
import io.fairboi.theme.LightGrayLight
import io.fairboi.theme.LightGreen
import io.fairboi.theme.LightOverlay
import io.fairboi.theme.LightPrimaryBack
import io.fairboi.theme.LightPrimaryLabel
import io.fairboi.theme.LightRed
import io.fairboi.theme.LightSecondaryBack
import io.fairboi.theme.LightTertiaryLabel
import io.fairboi.theme.White

internal val lightColorSchemeImpl = lightColorScheme(
    primary = LightBlue,
    onPrimary = White,
    primaryContainer = LightPrimaryBack,
    onPrimaryContainer = LightPrimaryLabel,
    inversePrimary = Black,
    secondary = LightGreen,
    onSecondary = White,
    secondaryContainer = LightSecondaryBack,
    onSecondaryContainer = LightSecondaryBack,
    tertiary = LightRed,
    onTertiary = White,
    tertiaryContainer = LightGray,
    onTertiaryContainer = LightTertiaryLabel,
    background = LightPrimaryBack,
    onBackground = LightPrimaryLabel,
    surface = LightGray,
    onSurface = LightGrayLight,
    surfaceVariant = LightGrayLight,
    onSurfaceVariant = White,
    surfaceTint = LightBlue,
    inverseSurface = DarkGrayLight,
    inverseOnSurface = DarkGrayLight,
    error = LightRed,
    onError = White,
    errorContainer = White,
    onErrorContainer = LightRed,
    outline = LightOverlay,
    outlineVariant = LightGrayLight,
    scrim = White,
    surfaceBright = LightGrayLight,
    surfaceContainer = LightGray,
    surfaceContainerHigh = LightGrayLight,
    surfaceContainerHighest = White,
    surfaceContainerLow = LightGray,
    surfaceContainerLowest = LightGray,
    surfaceDim = Black,
)

internal val darkColorSchemeImpl = darkColorScheme(
    primary = DarkBlue,
    onPrimary = Black,
    primaryContainer = DarkPrimaryBack,
    onPrimaryContainer = DarkPrimaryLabel,
    inversePrimary = White,
    secondary = DarkGreen,
    onSecondary = Black,
    secondaryContainer = DarkSecondaryBack,
    onSecondaryContainer = DarkSecondaryBack,
    tertiary = DarkRed,
    onTertiary = Black,
    tertiaryContainer = DarkGray,
    onTertiaryContainer = DarkTertiaryLabel,
    background = DarkPrimaryBack,
    onBackground = DarkPrimaryLabel,
    surface = DarkGray,
    onSurface = DarkGrayLight,
    surfaceVariant = DarkGrayLight,
    onSurfaceVariant = Black,
    surfaceTint = DarkBlue,
    inverseSurface = LightGrayLight,
    inverseOnSurface = LightGrayLight,
    error = DarkRed,
    onError = Black,
    errorContainer = Black,
    onErrorContainer = DarkRed,
    outline = DarkOverlay,
    outlineVariant = DarkGrayLight,
    scrim = Black,
    surfaceBright = DarkGrayLight,
    surfaceContainer = DarkGray,
    surfaceContainerHigh = DarkGrayLight,
    surfaceContainerHighest = Black,
    surfaceContainerLow = DarkGray,
    surfaceContainerLowest = DarkGray,
    surfaceDim = White,
)