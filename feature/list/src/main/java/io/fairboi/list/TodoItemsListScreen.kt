package io.fairboi.list

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.runtime.getValue
import io.fairboi.list.components.TodoItemsListView

@OptIn(ExperimentalMaterial3Api::class)
@Composable
 fun TodoItemsListScreen(
    viewModel: TodoItemsListViewModel,
    modifier: Modifier = Modifier, toSettingsScreen: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Todo Items") },
                actions = {
                    IconButton(onClick = toSettingsScreen) {
                        Icon(Icons.Filled.Settings, contentDescription = "Settings")
                    }
                }
            )
        }
    ) {innerPadding ->
        val uiState by viewModel.uiState.collectAsState()


        Text("Здесь будет список задач")

        when(uiState.listState){
            is TodoItemsUiState.ListState.Error -> {
                Text((uiState.listState as TodoItemsUiState.ListState.Error).message)
            }
            is TodoItemsUiState.ListState.Loading -> {
                CircularProgressIndicator(modifier = modifier.padding(innerPadding))
            }
            is TodoItemsUiState.ListState.Loaded -> {
                val items = (uiState.listState as TodoItemsUiState.ListState.Loaded).items
                TodoItemsListView(
                    items = items ,
                    onItemClicked = {},
                    onItemChecked = {},
                    onItemCreated = { viewModel.onTextTodoAdded(it) },
                    modifier = modifier.padding(innerPadding)
                )
            }
        }
    }
}