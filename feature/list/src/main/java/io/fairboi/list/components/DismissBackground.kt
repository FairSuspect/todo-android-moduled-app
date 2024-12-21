package io.fairboi.list.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.SwipeToDismissBoxState
import androidx.compose.material3.SwipeToDismissBoxValue
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import io.fairboi.list.R
import io.fairboi.theme.custom.MyAppTheme
import io.fairboi.ui.previews.BooleanPreviewParameterProvider
import io.fairboi.ui.previews.DefaultPreview
import io.fairboi.ui.previews.ItemPreviewTemplate
import io.fairboi.ui.previews.LayoutDirectionPreview
import io.fairboi.ui.previews.ThemePreview

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DismissBackground(dismissState: SwipeToDismissBoxState) {
    // Цвет берётся из Темы
    val deleteBackgroundColor = MyAppTheme.colors.red
    val checkBackgroundColor = MyAppTheme.colors.green

    val color = when (dismissState.dismissDirection) {
        SwipeToDismissBoxValue.StartToEnd -> deleteBackgroundColor
        SwipeToDismissBoxValue.EndToStart -> checkBackgroundColor
        SwipeToDismissBoxValue.Settled -> Color.Transparent

    }
    DismissBackgroundContent(color = color)

}

@Composable
private fun DismissBackgroundContent(color: Color) {
    val iconColor = MyAppTheme.colors.white
    Row(
        modifier = Modifier
            .fillMaxSize()
            .background(color)
            .padding(12.dp, 8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Icon(
            Icons.Default.Delete,
            contentDescription = stringResource(R.string.delete),
            tint = iconColor
        )
        Spacer(modifier = Modifier)
        Icon(
            Icons.Default.Check,
            contentDescription = stringResource(R.string.mark_done),
            tint = iconColor
        )
    }
}

@DefaultPreview
@ThemePreview
@LayoutDirectionPreview
@Composable
private fun DismissBackgroundPreview(
    @PreviewParameter(BooleanPreviewParameterProvider::class) isRed: Boolean
) {
    val color = if (isRed) MyAppTheme.colors.red else MyAppTheme.colors.green
    ItemPreviewTemplate {
        Box(
            modifier = Modifier.size(height = 48.dp, width = 300.dp)
        ) {

            DismissBackgroundContent(color = color)
        }
    }
}

