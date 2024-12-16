package io.fairboi.ui.previews

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

/**
 * Templates for previews
 */
@Composable
fun ItemPreviewTemplate(content: @Composable () -> Unit) {
    MaterialTheme {
        Box(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.surface)
                .padding(8.dp)
        ) {
            content()
        }
    }
}


@Composable
fun ScreenPreviewTemplate(content: @Composable (PaddingValues) -> Unit) {
    MaterialTheme {
        Scaffold(
            containerColor = MaterialTheme.colorScheme.background,
        ) {
            content(it)
        }
    }
}