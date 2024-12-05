package io.fairboi.list.components

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ListItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import java.text.SimpleDateFormat
import java.time.LocalDateTime

import java.util.Locale
import io.fairboi.domain.model.todo.TodoItem

@Composable
fun TodoItemTile(
    todoItem: TodoItem,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {},
    onCheckedChange: (Boolean) -> Unit = {},
) {

    ListItem(
        modifier = modifier.clickable(
            onClick = onClick,
        ),
        headlineContent = { Text(text = todoItem.text) },
        trailingContent = {
            Checkbox(
                checked = todoItem.done,
                onCheckedChange = onCheckedChange,
            )
        },
        supportingContent = if (todoItem.deadline != null) {
            {
                Text(text = todoItem.deadline!!.formatDate())
            }
        } else null

    )
}

fun LocalDateTime.formatDate(): String {
//    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//        val formatter =
//            DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM)
//        return format(formatter)
//
//    } else {
        val formatter = SimpleDateFormat("EEE, MMM d, yyyy HH:mm", Locale.getDefault())
        return formatter.format(this)
//    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview
@Composable
private fun TodoItemTilePreview() {
    val exampleTodoItem = TodoItem.fromText("Example todo item")
    Scaffold { innerPadding ->
        Column(modifier = Modifier.padding(innerPadding)) {
            TodoItemTile(
                todoItem = exampleTodoItem.copy(
                    done = true,
                    deadline = LocalDateTime.now()
                ),
            )
            TodoItemTile(
                todoItem = exampleTodoItem.copy(
                    done = true,
                ),
            )
            TodoItemTile(
                todoItem = exampleTodoItem,
            )
        }
    }
}