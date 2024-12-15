package io.fairboi.details.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
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

@Composable
fun TodoItemDetailsBody(
    todoItem: TodoItem,
    onEdit: (TodoItem) -> Unit,
    modifier: Modifier = Modifier,
    onDelete: () -> Unit = {},
) {

    var text by remember { mutableStateOf(todoItem.text) }
    val horizontalPadding = 16.dp
    LazyColumn(
        modifier = modifier
    ) {
        item {
            Column {
                val containerColor = MaterialTheme.colorScheme.surface
                val textColor = MaterialTheme.colorScheme.onSurface
                val placeFolderColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
                val cursorColor = MaterialTheme.colorScheme.primary
                val indicatorColor = Color.Transparent

                TextField(

                    value = text,
                    onValueChange = {
                        onEdit(
                            todoItem.copy(
                                text = it
                            )
                        )
                        text = it
                    },

                    maxLines = 10,
                    minLines = 5,
                    shape = RoundedCornerShape(6.dp),
                    colors = TextFieldDefaults.colors(
                        focusedIndicatorColor = indicatorColor,
                        unfocusedIndicatorColor = indicatorColor,
                        disabledIndicatorColor = indicatorColor,
                        focusedContainerColor = containerColor,
                        unfocusedContainerColor = containerColor,
                        disabledContainerColor = containerColor,
                        focusedTextColor = textColor,
                        unfocusedTextColor = textColor,
                        disabledTextColor = textColor,
                        focusedPlaceholderColor = placeFolderColor,
                        disabledPlaceholderColor = placeFolderColor,
                        unfocusedPlaceholderColor = placeFolderColor,
                        cursorColor = cursorColor
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontalPadding)
                        .shadow(8.dp),
                    placeholder = {
                        Text(
                            stringResource(R.string.todo_item_text_label),
                            style = MaterialTheme.typography.bodyMedium,

                            )
                    },
                )
                Box(
                    modifier = Modifier.padding(top = 12.dp)
                )
                TodoImportanceTile(
                    importance = todoItem.importance,
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


@Preview
@Composable
private fun TodoItemDetailsBodyPreview(
    @PreviewParameter(TodoItemDetailsProvider::class) todoItem: TodoItem
) {
    Scaffold {

        TodoItemDetailsBody(todoItem = todoItem, onEdit = {}, modifier = Modifier.padding(it))

    }
}

class TodoItemDetailsProvider : PreviewParameterProvider<TodoItem> {
    override val values: Sequence<TodoItem> = sequenceOf(
        TodoItem.fromText("Buy a beer"),
        TodoItem.blank()
    )

}