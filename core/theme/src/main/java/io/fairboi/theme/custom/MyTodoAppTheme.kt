package io.fairboi.theme.custom

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.remember
import io.fairboi.domain.model.ThemeSettings
import io.fairboi.theme.material.MyTodoAppMaterialTheme

object MyAppTheme {
    val colors: AppColors
        @Composable
        @ReadOnlyComposable
        get() = LocalColors.current

    val typography: AppTypography
        @Composable
        @ReadOnlyComposable
        get() = LocalTypography.current

    val dimensions: AppDimensions
        @Composable
        @ReadOnlyComposable
        get() = LocalDimensions.current
}
@Composable
fun MyTodoAppTheme (
    colors: AppColors? = null,
    typography: AppTypography = MyAppTheme.typography,
    dimensions: AppDimensions = MyAppTheme.dimensions,
    theme: ThemeSettings = ThemeSettings.System,
    content: @Composable () -> Unit
) {
    val isDarkTheme = when (theme) {
        ThemeSettings.System -> isSystemInDarkTheme()
        ThemeSettings.Light -> false
        ThemeSettings.Dark -> true
    }
    val appColors =
        colors ?: if (isDarkTheme) AppColors.dark else AppColors.light
    val rememberedColors = remember { appColors }

    CompositionLocalProvider(
        LocalColors provides rememberedColors,
        LocalDimensions provides dimensions,
        LocalTypography provides typography
    ) {
        MyTodoAppMaterialTheme(
            darkTheme = isDarkTheme,
        ) {
            content()
        }
    }
}