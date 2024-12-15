package io.fairboi.details.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun DeleteTodoTile(
    onClick: () -> Unit, modifier: Modifier = Modifier
) {
    val color = MaterialTheme.colorScheme.error
    ListItem(
        modifier = modifier.clickable(
            onClick = onClick,
        ), leadingContent = {
            Icon(Icons.Default.Delete, contentDescription = "Удалить задачу", tint = color)
        }, headlineContent = {
            Text("Удалить", color = color)
        })
}

@Preview
@Composable
private fun DeleteTodoTilePreview() {
    MaterialTheme {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.surface),


            ) {

            DeleteTodoTile(onClick = {})
        }

    }
}