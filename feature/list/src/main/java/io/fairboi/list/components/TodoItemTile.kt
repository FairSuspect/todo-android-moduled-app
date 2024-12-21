package io.fairboi.list.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import io.fairboi.domain.model.todo.TodoImportance
import io.fairboi.domain.model.todo.TodoItem
import io.fairboi.list.R
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
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                ImportanceIcon(importance = todoItem.importance, modifier = Modifier.padding(end = 3.dp))

                Text(
                    text = todoItem.text,
                    color = theme.colors.primary
                )
            }

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
                    color = theme.colors.tertiary
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
@Composable
fun ImportanceIcon(importance: TodoImportance, modifier: Modifier = Modifier) {
    val drawable = when (importance) {
        TodoImportance.HIGH -> R.drawable.ic_high_priority
        TodoImportance.NO -> R.drawable.ic_no_priority
        else -> null
    }
    val color = when (importance) {
        TodoImportance.HIGH -> MyAppTheme.colors.red
        else -> MyAppTheme.colors.gray
    }
    if (drawable != null) {
        Icon(
            painter = painterResource(id = drawable),
            contentDescription = stringResource(importance.displayNameRes),
            modifier = modifier,
            tint = color,
        )
    }
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