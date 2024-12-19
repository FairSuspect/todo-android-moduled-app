package io.fairboi.theme.custom

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import io.fairboi.theme.Black
import io.fairboi.theme.DarkBlue
import io.fairboi.theme.DarkDisableLabel
import io.fairboi.theme.DarkElevated
import io.fairboi.theme.DarkGray
import io.fairboi.theme.DarkGrayLight
import io.fairboi.theme.DarkGreen
import io.fairboi.theme.DarkOverlay
import io.fairboi.theme.DarkPrimaryBack
import io.fairboi.theme.DarkPrimaryLabel
import io.fairboi.theme.DarkRed
import io.fairboi.theme.DarkSecondaryBack
import io.fairboi.theme.DarkSecondaryLabel
import io.fairboi.theme.DarkSeparator
import io.fairboi.theme.DarkTertiaryLabel
import io.fairboi.theme.LightBlue
import io.fairboi.theme.LightDisableLabel
import io.fairboi.theme.LightElevated
import io.fairboi.theme.LightGray
import io.fairboi.theme.LightGrayLight
import io.fairboi.theme.LightGreen
import io.fairboi.theme.LightOverlay
import io.fairboi.theme.LightPrimaryBack
import io.fairboi.theme.LightPrimaryLabel
import io.fairboi.theme.LightRed
import io.fairboi.theme.LightSecondaryBack
import io.fairboi.theme.LightSecondaryLabel
import io.fairboi.theme.LightSeparator
import io.fairboi.theme.LightTertiaryLabel
import io.fairboi.theme.White

data class AppColors(
    val primary: Color,
    val secondary: Color,
    val tertiary: Color,
    val disable: Color,
    val separator: Color,
    val overlay: Color,
    val primaryBack: Color,
    val secondaryBack: Color,
    val elevated: Color,
    val red: Color,
    val green: Color,
    val blue: Color,
    val gray: Color,
    val grayLight: Color,
    val white: Color,
    val black: Color,
    val appBar: Color,
    val isDark: Boolean,
) {
    companion object {
        val light = AppColors(

            primary = LightPrimaryLabel,
            secondary = LightSecondaryLabel,
            tertiary = LightTertiaryLabel,
            disable = LightDisableLabel,
            separator = LightSeparator,
            overlay = LightOverlay,
            primaryBack = LightPrimaryBack,
            secondaryBack = LightSecondaryBack,
            elevated = LightElevated,
            red = LightRed,
            green = LightGreen,
            blue = LightBlue,
            gray = LightGray,
            grayLight = LightGrayLight,
            black = Black,
            appBar = LightPrimaryBack,
            white = White,
            isDark = false,
        )

        val dark = AppColors(
            primary = DarkPrimaryLabel,
            secondary = DarkSecondaryLabel,
            tertiary = DarkTertiaryLabel,
            disable = DarkDisableLabel,
            separator = DarkSeparator,
            overlay = DarkOverlay,
            primaryBack = DarkPrimaryBack,
            secondaryBack = DarkSecondaryBack,
            elevated = DarkElevated,
            red = DarkRed,
            green = DarkGreen,
            blue = DarkBlue,
            gray = DarkGray,
            grayLight = DarkGrayLight,
            black = Black,
            appBar = DarkSecondaryBack,
            white = White,
            isDark = true,
        )
    }
}

val LocalColors = staticCompositionLocalOf { AppColors.light }
