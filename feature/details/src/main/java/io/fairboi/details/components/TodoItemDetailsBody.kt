package io.fairboi.details.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import io.fairboi.details.R
import io.fairboi.domain.model.todo.TodoItem
import io.fairboi.theme.custom.MyAppTheme
import io.fairboi.ui.previews.DefaultPreview
import io.fairboi.ui.previews.ItemPreviewTemplate
import io.fairboi.ui.previews.ScreenPreviewTemplate
import io.fairboi.ui.previews.ThemePreview
import io.fairboi.ui.previews.TodoItemPreviewParameterProvider

@Composable
fun TodoItemDetailsBody(
    todoItem: TodoItem,
    onEdit: (TodoItem) -> Unit,
    modifier: Modifier = Modifier,
    onDelete: () -> Unit = {},
) {

    val horizontalPadding = 16.dp
    LazyColumn(
        modifier = modifier
    ) {
        item {
            Column {


                TodoDetailsTextField(
                    onTextChange = {
                        onEdit(
                            todoItem.copy(
                                text = it
                            )
                        )
                    },
                    initialText = todoItem.text,
                    modifier = Modifier.padding(horizontal = horizontalPadding)
                )
                Box(
                    modifier = Modifier.padding(top = 12.dp)
                )
                TodoImportanceTile(
                    importance = todoItem.importance,
                    onImportanceChanged = {
                        onEdit(
                            todoItem.copy(
                                importance = it
                            )
                        )
                    }
                )
                HorizontalDivider(
                    modifier = Modifier.padding(horizontal = horizontalPadding)
                )
                TodoDeadLineTile(
                    deadline = todoItem.deadline,
                    onChanged = {
                        onEdit(
                            todoItem.copy(
                                deadline = it
                            )
                        )
                    }
                )
            }
        }





        item {
            HorizontalDivider()

            DeleteTodoTile(onClick = onDelete)
        }


    }

}


@DefaultPreview
@ThemePreview
@Composable
private fun TodoItemDetailsBodyPreview(
    @PreviewParameter(TodoItemPreviewParameterProvider::class) todoItem: TodoItem
) {
    ItemPreviewTemplate {
        TodoItemDetailsBody(todoItem = todoItem, onEdit = {})

    }
}

