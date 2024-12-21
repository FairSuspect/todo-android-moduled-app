package io.fairboi.list.components

import androidx.compose.foundation.clickable
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewParameter
import io.fairboi.domain.model.todo.TodoItem
import io.fairboi.theme.custom.MyAppTheme
import io.fairboi.ui.previews.DefaultPreview
import io.fairboi.ui.previews.ItemPreviewTemplate
import io.fairboi.ui.previews.TodoItemPreviewParameterProvider
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@Composable
fun TodoItemTile(
    todoItem: TodoItem,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {},
    onCheckedChange: (Boolean) -> Unit = {},
) {
    val theme = MyAppTheme
    ListItem(
        modifier = modifier
            .clickable(onClick = onClick),
        colors = ListItemDefaults.colors(
            containerColor = theme.colors.secondaryBack
        ),
        headlineContent = {
            Text(
                text = todoItem.text,
                color = theme.colors.primary
            )
        },
        leadingContent = {
            MyTodoCheckbox(
                todoItem = todoItem,
                onCheckedChange = onCheckedChange,
                )
        },
        supportingContent = if (todoItem.deadline != null) {
            {
                Text(
                    text = todoItem.deadline!!.formatDate(),

                    color = theme.colors.primary
                )
            }
        } else null,

        trailingContent = {
            Icon(
                Icons.Outlined.Info,
                contentDescription = "Info",
                tint = theme.colors.tertiary,
            )
        }

    )
}

fun LocalDateTime.formatDate(): String {

    return this.format(DateTimeFormatter.ofPattern("dd MMMM yyyy"))
}


@DefaultPreview
@Composable
private fun TodoItemTilePreview(
    @PreviewParameter(TodoItemPreviewParameterProvider::class) todoItem: TodoItem
) {
    ItemPreviewTemplate {
        TodoItemTile(
            todoItem
        )
    }
}