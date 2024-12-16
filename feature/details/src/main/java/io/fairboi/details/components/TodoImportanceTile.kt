package io.fairboi.details.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import io.fairboi.details.R
import io.fairboi.domain.model.todo.TodoImportance
import io.fairboi.ui.previews.DefaultPreview
import io.fairboi.ui.previews.LanguagePreview
import io.fairboi.ui.previews.LayoutDirectionPreview
import io.fairboi.ui.previews.ThemePreview

@Composable
fun TodoImportanceTile(
    importance: TodoImportance,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    ListItem(
        modifier = modifier,
        headlineContent = {
            Text(context.getString(R.string.importance_label))
        },
        supportingContent = {
            ImportanceValueText(importance = importance)
        }
    )

}

@Composable
private fun ImportanceValueText(importance: TodoImportance) {
    val text = when (importance) {
        TodoImportance.LOW -> stringResource(R.string.importance_low)
        TodoImportance.BASIC -> stringResource(R.string.importance_basic)
        TodoImportance.HIGH -> "!! ${stringResource(R.string.importance_high)}"
    }

    val color = when (importance) {
        TodoImportance.HIGH -> MaterialTheme.colorScheme.error
        TodoImportance.LOW -> MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
        else -> MaterialTheme.colorScheme.onSurface
    }

    Text(
        text,
        color = color
    )
}

@DefaultPreview
@LanguagePreview
@ThemePreview
@LayoutDirectionPreview
@Composable
private fun TodoImportanceTilePreview(
    @PreviewParameter(TodoImportanceProvider::class) importance: TodoImportance
) {
    MaterialTheme {
        Box(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.surface)
                .padding(8.dp)
        ) {

            TodoImportanceTile(
                importance = importance,

                )
        }
    }
}

class TodoImportanceProvider : PreviewParameterProvider<TodoImportance> {
    override val values: Sequence<TodoImportance> = sequenceOf(
        TodoImportance.LOW,
        TodoImportance.BASIC,
        TodoImportance.HIGH
    )

}