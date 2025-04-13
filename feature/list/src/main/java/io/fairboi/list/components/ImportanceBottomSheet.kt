package io.fairboi.list.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import io.fairboi.domain.model.todo.TodoImportance
import io.fairboi.theme.custom.MyAppTheme
import io.fairboi.ui.previews.ItemPreviewTemplate
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ImportanceBottomSheet(
    initialImportance: TodoImportance,
    onDismissed: (TodoImportance?) -> Unit,
    modifier: Modifier = Modifier
) {
    var importance by remember { mutableStateOf(initialImportance) }
    val sheetState = rememberModalBottomSheetState(

    )
    val scope = rememberCoroutineScope()
    ModalBottomSheet(

        tonalElevation = BottomSheetDefaults.Elevation,
        sheetState = sheetState,
        containerColor = MyAppTheme.colors.secondaryBack,
        contentColor = MyAppTheme.colors.primary,
        scrimColor = MyAppTheme.colors.overlay,
        modifier = Modifier.background(
            MyAppTheme.colors.primaryBack
        ),
        shape = BottomSheetDefaults.ExpandedShape,
        dragHandle = {
            BottomSheetDefaults.DragHandle(
                color = MyAppTheme.colors.secondary
            )
        },
        onDismissRequest = {
            val selectedImportance = if (importance == initialImportance) null else importance
            onDismissed(selectedImportance)
        }

    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                "Важность задачи",
                modifier = Modifier.padding(bottom = 16.dp),
                style = MyAppTheme.typography.title
            )
            Row(
                modifier = Modifier.fillMaxWidth(),

                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                for (imp in TodoImportance.entries)
                    ImportanceButton(
                        importance = imp,
                        onClick = {
                            importance = imp
                        },
                        isSelected = importance == imp,

                        )
            }


            FilledTonalButton(
                onClick = {
                    scope.launch {
                        sheetState.hide()
                    }.invokeOnCompletion {
                        if (!sheetState.isVisible) {
                            val selectedImportance =
                                if (importance == initialImportance) null else importance
                            onDismissed(selectedImportance)
                        }

                    }
                },
                modifier = modifier.fillMaxWidth(),
                colors = MyAppTheme.filledButtonColors,
            ) {
                Text("Save")
            }
        }

    }
}

@Composable
fun ImportanceButton(
    importance: TodoImportance,
    onClick: () -> Unit,
    isSelected: Boolean = false,
) {
    if (isSelected) {
        FilledTonalButton(
            onClick = {},
            colors = MyAppTheme.filledButtonColors,

            ) {
            Text(stringResource(importance.displayNameRes))
        }
    } else {
        OutlinedButton(
            onClick = onClick,
//            modifier = modifier.background(
//               MyAppTheme.colors.secondaryBack
//            )
        ) {


            Text(stringResource(importance.displayNameRes))
        }
    }


}

@Preview
@Composable
private fun ImportanceBottomSheetPreview(@PreviewParameter(TodoImportanceProvider::class) importance: TodoImportance) {
    ItemPreviewTemplate {
        ImportanceBottomSheet(
            initialImportance = importance,
            onDismissed = {}
        )
    }
}

class TodoImportanceProvider : PreviewParameterProvider<TodoImportance> {
    override val values: Sequence<TodoImportance> = sequenceOf(
        TodoImportance.NO,
        TodoImportance.LOW,
        TodoImportance.HIGH
    )

}