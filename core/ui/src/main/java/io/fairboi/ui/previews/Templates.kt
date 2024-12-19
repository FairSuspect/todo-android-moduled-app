package io.fairboi.ui.previews

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import io.fairboi.domain.model.ThemeSettings
import io.fairboi.theme.custom.MyAppTheme
import io.fairboi.theme.custom.MyTodoAppTheme

/**
 * Templates for previews
 */
@Composable
fun ItemPreviewTemplate(content: @Composable () -> Unit) {
    MyTodoAppTheme(
        theme = ThemeSettings.Dark
    ) {
        Box(
            modifier = Modifier
                .background(MyAppTheme.colors.primaryBack)
                .padding(8.dp)
        ) {
            content()
        }
    }
}


@Composable
fun ScreenPreviewTemplate(
    themeMode: ThemeSettings = ThemeSettings.System,
    content: @Composable (PaddingValues) -> Unit) {
    MyTodoAppTheme(
        theme = themeMode
    ) {
        Scaffold(
            containerColor = MyAppTheme.colors.primaryBack,
        ) {
            content(it)
        }
    }
}