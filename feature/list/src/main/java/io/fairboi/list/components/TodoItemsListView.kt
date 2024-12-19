package io.fairboi.list.components

import android.Manifest
import androidx.annotation.RequiresPermission
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import io.fairboi.domain.model.todo.TodoId
import io.fairboi.domain.model.todo.TodoItem
import io.fairboi.theme.custom.MyAppTheme
import io.fairboi.ui.previews.DefaultPreview
import io.fairboi.ui.previews.LayoutDirectionPreview
import io.fairboi.ui.previews.ScreenPreviewTemplate
import io.fairboi.ui.previews.ThemePreview
import io.fairboi.ui.previews.TodoItemPreviewParameterProvider

@Composable
@RequiresPermission(Manifest.permission.VIBRATE)
fun TodoItemsListView(
    items: List<TodoItem>,
    onItemClicked: (TodoItem) -> Unit,
    onItemChecked: (TodoItem) -> Unit,
    onItemCreated: (String) -> Unit,
    onItemRemoved: (itemId: TodoId) -> Unit,
    modifier: Modifier = Modifier,
    scrollState: LazyListState = rememberLazyListState(),

    ) {
    val shape = RoundedCornerShape(MyAppTheme.dimensions.cardCornersRadius)
    val backgroundColor = MyAppTheme.colors.secondaryBack
// Карточка с тайлами текстом чекбоксов, закруглённая по углам
    LazyColumn(
        modifier = modifier
            .padding(8.dp)
//            .border(shape = shape, border = BorderStroke(1.dp, Color.Transparent))
            .background(color = backgroundColor, shape = shape)
        ,
        state = scrollState,
    ) {
        items(items.size) {
            val item = items[it]

            DismissibleTotoItemTile(
                onRemove = { onItemRemoved(item.id) },
                todoItem = item,
                onClick = { onItemClicked(item) },
                onCheckedChange = { checked -> onItemChecked(item.copy(done = checked)) },
                modifier = Modifier.background(backgroundColor)

            )
        }
        item {
            TodoItemCreatorTextField(
                onItemCreated = onItemCreated,
                modifier = Modifier
                    .background(backgroundColor)
            )
        }
    }
}


@RequiresPermission(Manifest.permission.VIBRATE)
@DefaultPreview
@ThemePreview
@LayoutDirectionPreview
@Composable
private fun TodoItemsListViewPreview(

) {


    ScreenPreviewTemplate {
        TodoItemsListView(
            items = TodoItemPreviewParameterProvider().values.toList(),
            onItemClicked = {},
            onItemChecked = {},
            onItemCreated = {},
            onItemRemoved = {},

            )
    }


}