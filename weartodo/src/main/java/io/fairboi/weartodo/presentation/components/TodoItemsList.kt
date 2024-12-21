package io.fairboi.weartodo.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.key
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.wear.compose.foundation.lazy.ScalingLazyColumn
import androidx.wear.compose.foundation.lazy.rememberScalingLazyListState
import androidx.wear.compose.material.MaterialTheme
import androidx.wear.compose.material.PositionIndicator
import androidx.wear.compose.material.Scaffold
import io.fairboi.domain.model.ThemeSettings
import io.fairboi.domain.model.todo.TodoItem
import io.fairboi.theme.custom.MyTodoAppTheme
import io.fairboi.ui.previews.TodoItemPreviewParameterProvider
import io.fairboi.weartodo.presentation.previews.DefaultWearPreview

@Composable
internal fun TodoItemsList(
    todoItems: List<TodoItem>
) {
    val listState = rememberScalingLazyListState()

    Scaffold(
        positionIndicator = {
            PositionIndicator(scalingLazyListState = listState)
        }
    ) {

        ScalingLazyColumn(
            state = listState,

            ) {
            items(todoItems.size) {
                val todoItem = todoItems[it]
                // Используем key для предотвращения ненужных перерисовок
                key(todoItem.id) {
                    TodoItemTile(todoItem = todoItem)
                }
            }
        }
    }
}


@DefaultWearPreview
@Composable
fun TodoListPreview(
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
            TodoItemsList(
                TodoItemPreviewParameterProvider().values.toList(),
            )
        }
    }
}

