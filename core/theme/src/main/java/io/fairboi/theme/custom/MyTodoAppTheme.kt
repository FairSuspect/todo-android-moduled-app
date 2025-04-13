package io.fairboi.theme.custom

import android.util.Log
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ButtonColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.remember
import io.fairboi.domain.model.ThemeSettings
import io.fairboi.theme.custom.AppColors.Companion.updateColorsFrom
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

    val filledButtonColors: ButtonColors
        @Composable
        @ReadOnlyComposable
        get() = ButtonColors(
            containerColor = colors.blue,
            contentColor = colors.white,
            disabledContainerColor =colors.disable,
            disabledContentColor =   colors.white
        )
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
    val rememberedColors = remember { appColors }.apply { updateColorsFrom(appColors) }
    Log.d("MyTodoAppTheme", "isDarkTheme: $isDarkTheme (from $theme)")
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