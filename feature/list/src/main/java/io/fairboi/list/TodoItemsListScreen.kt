package io.fairboi.list

import android.Manifest
import androidx.annotation.RequiresPermission
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.LargeFloatingActionButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import io.fairboi.domain.model.todo.TodoId
import io.fairboi.domain.model.todo.TodoItem
import io.fairboi.list.components.ImportanceBottomSheet
import io.fairboi.list.components.TodoItemsListView
import io.fairboi.list.components.TodosAppBar
import io.fairboi.theme.custom.MyAppTheme


@OptIn(ExperimentalMaterial3Api::class)
@RequiresPermission(Manifest.permission.VIBRATE)
@Composable
fun TodoItemsListScreen(
    viewModel: TodoItemsListViewModel,
    toDetailsScreen: (TodoId?) -> Unit,

    modifier: Modifier = Modifier, toSettingsScreen: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsState()
    val scrollState = rememberLazyListState()
    var showImportanceBottomSheet by remember { mutableStateOf<TodoItem?>(null) }

    Scaffold(
        topBar = {
            TodosAppBar(
                viewModel = viewModel,
                toSettingsScreen = toSettingsScreen,
                scrollState = scrollState,
                modifier = Modifier.background(MyAppTheme.colors.primaryBack)
            )
        },
        floatingActionButton = {
            LargeFloatingActionButton(
                onClick = {
                    toDetailsScreen(null)
                },
                containerColor = MyAppTheme.colors.blue,
                contentColor = MyAppTheme.colors.white

            ) {
                Icon(Icons.Filled.Add, "Floating action button.")
            }
        },
        containerColor = MyAppTheme.colors.primaryBack,
    ) { innerPadding ->

        when (uiState.listState) {
            is TodoItemsUiState.ListState.Error -> {
                Text((uiState.listState as TodoItemsUiState.ListState.Error).message)
            }

            is TodoItemsUiState.ListState.Loading -> {
                CircularProgressIndicator(modifier = modifier.padding(innerPadding))
            }

            is TodoItemsUiState.ListState.Loaded -> {
                val items = (uiState.listState as TodoItemsUiState.ListState.Loaded).items

                TodoItemsListView(
                    items = items,
                    onItemClicked = { toDetailsScreen(it.id) },
                    onItemChecked = { viewModel.onItemChecked(it) },
                    onItemCreated = { viewModel.onTextTodoAdded(it) },
                    onItemRemoved = { viewModel.onItemRemoved(it) },
                    onImportanceClick = {
                        showImportanceBottomSheet = it
                    },
                    scrollState = scrollState,
                    modifier = modifier.padding(innerPadding)
                )
                showImportanceBottomSheet?.let { todoItem ->
                    ImportanceBottomSheet(
                        modifier = modifier.padding(16.dp),
                        initialImportance = todoItem.importance,
                        onDismissed = {

                            showImportanceBottomSheet = null
                            if (it == null) return@ImportanceBottomSheet
                            viewModel.onItemImportanceChanged(todoItem, it)
                        }
                    )
                }
            }
        }
    }

}


