package io.fairboi.details

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import io.fairboi.details.components.DetailedTopBar
import io.fairboi.details.components.TodoItemDetailsBody
import io.fairboi.domain.model.todo.TodoId
import io.fairboi.domain.model.todo.TodoItem
import java.time.Instant
import java.time.LocalDateTime

@Composable
fun TodoItemDetailsScreen(
    viewModel: TodoDetailsViewModel,
    todoId: TodoId?,
    modifier: Modifier = Modifier,
) {
    val uiState = viewModel.uiState.collectAsState()


    TodoDetailsContent(
        uiState = uiState.value,
        onTryAgain = {
            viewModel.tryAgain(todoId)
        },
        onEdit = {
            viewModel.edit(it)
        },
        modifier = modifier
    )


}

@Composable
internal fun TodoDetailsContent(
    uiState: TodoDetailsUiState,
    modifier: Modifier = Modifier,
    onTryAgain: () -> Unit = {},
    onEdit: (TodoItem) -> Unit = {},
) {
    Scaffold(
        topBar = {
            DetailedTopBar(
                onBack = {},
                onSave = {},
                modifier = modifier
            )
        }
    ) { innerPadding ->

        val todoItemBody = when (val state = uiState) {
            is TodoDetailsUiState.Loading -> {
                // Должен отображаться по центру
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = modifier
                        .padding(innerPadding)

                        .fillMaxSize()
                ) {

                    CircularProgressIndicator(
                        modifier = modifier
                            .padding(innerPadding)
                    )
                }

            }

            is TodoDetailsUiState.Loaded -> {
                TodoItemDetailsBody(
                    todoItem = state.item,
                    onEdit = {
                        onEdit(it)
                    },
                    modifier = modifier
                        .padding(innerPadding)
                )
            }

            is TodoDetailsUiState.Error -> {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = modifier
                        .fillMaxSize()
                        .padding(innerPadding)
                ) {
                    Column {

                        Text(
                            state.message,
                            modifier = modifier.padding(innerPadding),
                            textAlign = TextAlign.Center
                        )
                        TextButton(
                            onClick = { onTryAgain() },
                            modifier = modifier.padding(innerPadding)
                        ) {
                            Text(
                                "Try again", textAlign = TextAlign.Center
                            )
                        }
                    }
                }
            }
        }
        todoItemBody

    }
}

private class TodoDetailsUiStatePreviewProvider : PreviewParameterProvider<TodoDetailsUiState> {
    override val values: Sequence<TodoDetailsUiState> = sequenceOf(
        TodoDetailsUiState.Loading,
        TodoDetailsUiState.Error("Error"),
        TodoDetailsUiState.Loaded(TodoItem.blank()),
        TodoDetailsUiState.Loaded(
            TodoItem.fromText("Task 1"),
            TodoDetailsUiState.TodoEditMode.EDIT
        ),
        TodoDetailsUiState.Loaded(
            TodoItem(
                text = "Task with deadline",
                deadline = LocalDateTime.now().plusDays(1),
                id = "testId",
                done = false,
                createdAt = Instant.now(),

                ),
            TodoDetailsUiState.TodoEditMode.EDIT
        )

    )
}

@Preview
@Composable
private fun EditItemScreenPreview(
    @PreviewParameter(TodoDetailsUiStatePreviewProvider::class) uiState: TodoDetailsUiState
) {
    Scaffold {
        var state by remember {
            mutableStateOf(
                uiState
            )
        }
        TodoDetailsContent(
            uiState = state,
            modifier = Modifier.padding(it)
        )
    }


}