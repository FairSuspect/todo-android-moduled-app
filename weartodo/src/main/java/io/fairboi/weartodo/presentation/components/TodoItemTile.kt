package io.fairboi.weartodo.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.wear.compose.material.Checkbox
import androidx.wear.compose.material.MaterialTheme
import androidx.wear.compose.material.Text
import io.fairboi.domain.model.ThemeSettings
import io.fairboi.domain.model.todo.TodoItem
import io.fairboi.theme.custom.MyAppTheme
import io.fairboi.theme.custom.MyTodoAppTheme
import io.fairboi.ui.previews.TodoItemPreviewParameterProvider
import io.fairboi.weartodo.presentation.previews.DefaultWearPreview

@Composable
fun TodoItemTile(
    todoItem: TodoItem,
    modifier: Modifier = Modifier,
    onCheckedChange: (Boolean) -> Unit = {}
) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(24.dp))
            .background(MyAppTheme.colors.primaryBack)
    ) {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .sizeIn(minHeight = 48.dp)
                .padding(horizontal = 8.dp, vertical = 4.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = todoItem.text,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                style = MaterialTheme.typography.body1,
                modifier = Modifier.weight(4f)
            )
            Checkbox(
                checked = todoItem.done,
                onCheckedChange = onCheckedChange,
                modifier = Modifier.padding(start = 8.dp).weight(1f)
            )
        }
    }
}

@DefaultWearPreview
@Composable
fun TodoItemTilePreview(
    @PreviewParameter(TodoItemPreviewParameterProvider::class) todoItem: TodoItem
) {
    MyTodoAppTheme(
        theme = ThemeSettings.Dark
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colors.background),
            contentAlignment = Alignment.Center
        ) {
            TodoItemTile(todoItem = todoItem)
        }
    }
}
