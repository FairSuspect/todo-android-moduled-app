package io.fairboi.domain.model

import androidx.annotation.StringRes
import io.fairboi.mytodoapp.domain.R

enum class ThemeSettings (@StringRes val titleResId: Int) {
    Light(R.string.light_theme),
    Dark(R.string.dark_theme),
    System(R.string.system_theme),
}