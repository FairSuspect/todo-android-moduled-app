package io.fairboi.list.components

import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.PreviewParameter
import io.fairboi.domain.model.todo.TodoImportance
import io.fairboi.domain.model.todo.TodoItem
import io.fairboi.theme.custom.MyAppTheme
import io.fairboi.ui.previews.DefaultPreview
import io.fairboi.ui.previews.ItemPreviewTemplate
import io.fairboi.ui.previews.ThemePreview
import io.fairboi.ui.previews.TodoItemPreviewParameterProvider

@Composable
fun MyTodoCheckbox(
    todoItem: TodoItem,
    onCheckedChange: (Boolean) -> Unit,
) {
    val isImportant = todoItem.importance == TodoImportance.HIGH
    val uncheckedColor = if (isImportant) MyAppTheme.colors.red else MyAppTheme.colors.separator
    val backgroundColor = if (isImportant) MyAppTheme.colors.red.copy(alpha = 0.16f) else Color.Transparent
    Checkbox(
        checked = todoItem.done,
        onCheckedChange = onCheckedChange,
        colors = CheckboxDefaults.colors(
            checkmarkColor = MyAppTheme.colors.secondaryBack,
            checkedColor = MyAppTheme.colors.green,
            uncheckedColor =uncheckedColor,
        ).copy(
            uncheckedBoxColor = backgroundColor
        )
    )
}

@DefaultPreview
@ThemePreview
@Composable
private fun MyCheckboxPreview(
    @PreviewParameter(TodoItemPreviewParameterProvider::class) todoItem: TodoItem
) {
    ItemPreviewTemplate {
        MyTodoCheckbox(
            todoItem,
            onCheckedChange =  {}
        )
    }
}

