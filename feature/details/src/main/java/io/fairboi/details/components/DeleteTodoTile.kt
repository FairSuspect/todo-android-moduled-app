package io.fairboi.details.components

import androidx.compose.foundation.clickable
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import io.fairboi.details.R
import io.fairboi.theme.custom.MyAppTheme
import io.fairboi.ui.previews.DefaultPreview
import io.fairboi.ui.previews.ItemPreviewTemplate
import io.fairboi.ui.previews.LanguagePreview
import io.fairboi.ui.previews.LayoutDirectionPreview
import io.fairboi.ui.previews.ThemePreview

@Composable
fun DeleteTodoTile(
    onClick: () -> Unit, modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val color = MyAppTheme.colors.red
    ListItem(
        modifier = modifier.clickable(
            onClick = onClick,
        ), leadingContent = {
            Icon(
                Icons.Default.Delete,
                contentDescription = context.getString(R.string.delete_task),
                tint = color
            )
        }, headlineContent = {
            Text(context.getString(R.string.delete), color = color)
        },
        colors = ListItemDefaults.colors(
            containerColor = MyAppTheme.colors.primaryBack,
        )
    )
}

@DefaultPreview
@ThemePreview
@LanguagePreview
@LayoutDirectionPreview
@Composable
private fun DeleteTodoTilePreview() {
    ItemPreviewTemplate {
        DeleteTodoTile(onClick = {})
    }
}