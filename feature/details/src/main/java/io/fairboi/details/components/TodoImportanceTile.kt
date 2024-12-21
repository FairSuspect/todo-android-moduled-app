package io.fairboi.details.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import io.fairboi.details.R
import io.fairboi.domain.model.todo.TodoImportance
import io.fairboi.theme.custom.MyAppTheme
import io.fairboi.ui.previews.DefaultPreview
import io.fairboi.ui.previews.ItemPreviewTemplate
import io.fairboi.ui.previews.LanguagePreview
import io.fairboi.ui.previews.LayoutDirectionPreview
import io.fairboi.ui.previews.ThemePreview

@Composable
fun TodoImportanceTile(
    importance: TodoImportance,
    onImportanceChanged: (importance: TodoImportance) -> Unit,
    modifier: Modifier = Modifier,
    initiallyExpanded: Boolean = false,
) {
    val context = LocalContext.current
    var expanded by remember { mutableStateOf(initiallyExpanded) }
    Box {
        ListItem(
            modifier = modifier.clickable(
                onClick = { expanded = !expanded }
            ),
            headlineContent = {
                Text(
                    context.getString(R.string.importance_label),
                    color = MyAppTheme.colors.primary,
                )
            },
            supportingContent = {
                ImportanceValueText(importance = importance)
            },
            colors = ListItemDefaults.colors(
                containerColor = MyAppTheme.colors.primaryBack,

                )
        )

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier
                .background(MyAppTheme.colors.primaryBack)
        ) {
            for (importance in TodoImportance.entries) {
                val color = when (importance) {
                    TodoImportance.HIGH -> MyAppTheme.colors.red
                    else -> MyAppTheme.colors.primary
                }
                var text = stringResource(importance.displayNameRes)
                if (importance == TodoImportance.HIGH) {
                    text = "!! $text"
                }
                DropdownMenuItem(
                    text = {
                        Text(
                            text,
                            color = color,
                        )

                    },
                    onClick = {
                        expanded = false
                        onImportanceChanged(importance)
                    }
                )
            }
        }
    }


}

@Composable
private fun ImportanceValueText(importance: TodoImportance) {
    var text = stringResource(importance.displayNameRes)
    if (importance == TodoImportance.HIGH) {
        text = "!! $text"
    }

    val color = when (importance) {
        TodoImportance.HIGH -> MyAppTheme.colors.red
        TodoImportance.NO -> MyAppTheme.colors.tertiary
        else -> MyAppTheme.colors.primary
    }

    Text(
        text,
        color = color
    )
}

@DefaultPreview
@LanguagePreview
@ThemePreview
@LayoutDirectionPreview
@Composable
private fun TodoImportanceTilePreview(
    @PreviewParameter(TodoImportanceProvider::class) importance: TodoImportance,
) {
    ItemPreviewTemplate {
        TodoImportanceTile(
            importance = importance,
            onImportanceChanged = {}
        )
    }

}

@DefaultPreview
@ThemePreview
@LayoutDirectionPreview
@Composable
private fun ExpandedTodoImportanceTilePreview(
    @PreviewParameter(TodoImportanceProvider::class) importance: TodoImportance,
) {
    ItemPreviewTemplate {
        TodoImportanceTile(
            importance = importance,
            onImportanceChanged = {},
            initiallyExpanded = true
        )
    }

}

class TodoImportanceProvider : PreviewParameterProvider<TodoImportance> {
    override val values: Sequence<TodoImportance> = sequenceOf(
        TodoImportance.NO,
        TodoImportance.LOW,
        TodoImportance.HIGH
    )

}