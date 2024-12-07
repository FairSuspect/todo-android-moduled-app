package io.fairboi.list.components

import android.Manifest
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.annotation.RequiresPermission
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import io.fairboi.domain.model.todo.TodoId
import io.fairboi.domain.model.todo.TodoItem

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

    LazyColumn(
        modifier = modifier,
        state = scrollState,
    ) {
        items(items.size) {
            val item = items[it]

            DismissibleTotoItemTile(
                onRemove = { onItemRemoved(item.id) },
                todoItem = item,
                onClick = { onItemClicked(item) },
                onCheckedChange = { checked -> onItemChecked(item.copy(done = checked)) }
            )
        }
        item {
            TodoItemCreator(onItemCreated = onItemCreated)
        }
    }
}

@Composable
fun TodoItemCreator(onItemCreated: (String) -> Unit) {
    var text by remember { mutableStateOf("") }
    ListItem(headlineContent = {
        TextField(
            value = text,
            onValueChange = { text = it },
            label = { Text("Add new task") },
            placeholder = { Text("Task name") },
            singleLine = true,
            keyboardActions = KeyboardActions(
                onDone = {
                    onItemCreated(text)
                    text = ""
                }
            ),
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Done
            ),
            trailingIcon = {
                Icon(Icons.Filled.Add, contentDescription = "Add")
            },
            modifier = Modifier.fillMaxWidth()
        )
    })
}


@RequiresApi(Build.VERSION_CODES.O)
@RequiresPermission(Manifest.permission.VIBRATE)
@Preview
@Composable
private fun TodoItemsListViewPreview() {
    // Генерируем 10 задач из текста
    val items = List(10) {
        TodoItem.fromText("Task $it").copy(done = it % 2 == 0)
    }
    Scaffold { innerPadding ->

        TodoItemsListView(
            items = items,
            onItemClicked = {},
            onItemChecked = {},
            onItemCreated = {},
            onItemRemoved = {},
            modifier = Modifier.padding(innerPadding)
        )

    }
}