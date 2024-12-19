package io.fairboi.ui.previews

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.content.res.Configuration.UI_MODE_TYPE_NORMAL
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview

@Preview(
    name = "Default Mode",
    showBackground = true,
    uiMode = UI_MODE_NIGHT_NO,
    locale = "en",
    fontScale = 1.0f
)
annotation class DefaultPreview

@Preview(
    name = "Dark Mode",
    uiMode =  UI_MODE_NIGHT_YES or UI_MODE_TYPE_NORMAL,
)
annotation class ThemePreview

@Preview(
    name = "Font Scale 1.5",
    showBackground = true,
    fontScale = 1.5f
)
@Preview(
    name = "Font Scale 2.0",
    showBackground = true,
    fontScale = 2.0f
)
annotation class FontScalePreview

@Preview(
    name = "Landscape Mode",
    showBackground = true,
    device = Devices.AUTOMOTIVE_1024p,
    widthDp = 640
)
annotation class OrientationPreview

@Preview(
    name = "Low Dpi (More space)",
    device = "spec:width=411dp,height=891dp,dpi=356",
)@Preview(
    name = "High Dpi (Less space)",
    device = "spec:width=411dp,height=891dp,dpi=540",
)
annotation class DensityPreview

@Preview(name = "Russian language", locale = "ru")
annotation class LanguagePreview

@Preview(name = "Right-To-Left", locale = "ar")
annotation class LayoutDirectionPreview