package io.fairboi.details.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import io.fairboi.domain.model.todo.TodoItem

@Composable
fun TodoItemDetailsGeneralInfo(
    todoItem: TodoItem,
    modifier: Modifier = Modifier) {


    Box (
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.primaryContainer)
    ) {
        Column {
            TextField(
                value = todoItem.text,
                onValueChange = { },
                readOnly = true,
                modifier = modifier
            )
            Text(
                text = todoItem.deadline.toString(),
                modifier = modifier
            )
        }
    }
}

@Preview
@Composable
private fun TodoItemDetailsGeneralInfoPreview() {
    val todoItem = TodoItem.fromText("Task 1")
    TodoItemDetailsGeneralInfo(todoItem = todoItem)


}