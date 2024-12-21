package io.fairboi.list.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.PreviewParameter
import io.fairboi.theme.custom.MyAppTheme
import io.fairboi.ui.previews.DefaultPreview
import io.fairboi.ui.previews.ItemPreviewTemplate
import io.fairboi.ui.previews.LanguagePreview
import io.fairboi.ui.previews.LayoutDirectionPreview
import io.fairboi.ui.previews.TextPreviewParameterProvider
import io.fairboi.ui.previews.ThemePreview

@Composable
fun TodoItemCreatorTextField(
    onItemCreated: (String) -> Unit,
    modifier: Modifier = Modifier,
    initialText: String = "",
) {
    var text by remember { mutableStateOf(initialText) }
    ListItem(
        colors = ListItemDefaults.colors(
            containerColor = MyAppTheme.colors.secondaryBack
        ), headlineContent = {
            TextField(
                value = text,
                onValueChange = { text = it },

                placeholder = { Text("Новое") },
                singleLine = true,
                keyboardActions = KeyboardActions(
                    onDone = {
                        onItemCreated(text)
                        text = ""
                    }),
                keyboardOptions = KeyboardOptions.Default.copy(
                    imeAction = ImeAction.Done
                ),
                leadingIcon = {
                    Icon(
                        Icons.Filled.Add,
                        contentDescription = "Add",
                        tint = MyAppTheme.colors.separator
                    )
                },

                colors = TextFieldDefaults.colors(
                    unfocusedPlaceholderColor = MyAppTheme.colors.tertiary,
                    focusedPlaceholderColor = MyAppTheme.colors.tertiary,
                    focusedTextColor = MyAppTheme.colors.primary,
                    unfocusedTextColor = MyAppTheme.colors.primary,
                    focusedContainerColor = MyAppTheme.colors.secondaryBack,
                    unfocusedContainerColor = MyAppTheme.colors.secondaryBack,
                    cursorColor = MyAppTheme.colors.primary,
                ),
                modifier = modifier.fillMaxWidth()

            )
        })
}

@DefaultPreview
@ThemePreview
@LayoutDirectionPreview
@LanguagePreview
@Composable
private fun TodoItemTextFieldPreview() {
    ItemPreviewTemplate {
        TodoItemCreatorTextField(onItemCreated = {})
    }
}

@DefaultPreview
@ThemePreview
@LayoutDirectionPreview
@LanguagePreview
@Composable
private fun TodoItemTextFieldWithTextPreview(
    @PreviewParameter(TextPreviewParameterProvider::class) text: String
) {
    ItemPreviewTemplate {
        TodoItemCreatorTextField(onItemCreated = {}, initialText = text)
    }
}
