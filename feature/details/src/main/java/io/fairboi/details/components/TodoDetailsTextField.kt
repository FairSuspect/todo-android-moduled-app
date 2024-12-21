package io.fairboi.details.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import io.fairboi.details.R
import io.fairboi.theme.custom.MyAppTheme

@Composable
fun TodoDetailsTextField(
    onTextChange: (String) -> Unit,
    modifier: Modifier = Modifier, initialText: String = ""
) {
    var text by remember { mutableStateOf(initialText) }
    val containerColor = MyAppTheme.colors.secondaryBack
    val textColor = MyAppTheme.colors.primary
    val placeFolderColor = MyAppTheme.colors.tertiary
    val cursorColor = MyAppTheme.colors.primary
    val indicatorColor = Color.Transparent
    TextField(
        value = text,
        onValueChange = {
            text = it
            onTextChange(it)
        },
        keyboardOptions = KeyboardOptions.Default.copy(
            capitalization = androidx.compose.ui.text.input.KeyboardCapitalization.Sentences,
            showKeyboardOnFocus = false,
            imeAction = androidx.compose.ui.text.input.ImeAction.None
        ),
        minLines = 5,
        shape = RoundedCornerShape(6.dp),
        colors = TextFieldDefaults.colors(
            focusedIndicatorColor = indicatorColor,
            unfocusedIndicatorColor = indicatorColor,
            disabledIndicatorColor = indicatorColor,
            focusedContainerColor = containerColor,
            unfocusedContainerColor = containerColor,
            disabledContainerColor = containerColor,
            focusedTextColor = textColor,
            unfocusedTextColor = textColor,
            disabledTextColor = textColor,
            focusedPlaceholderColor = placeFolderColor,
            disabledPlaceholderColor = placeFolderColor,
            unfocusedPlaceholderColor = placeFolderColor,
            cursorColor = cursorColor
        ),
        modifier = modifier
            .fillMaxWidth()

            .shadow(8.dp),
        placeholder = {
            Text(
                stringResource(R.string.todo_item_text_label),
                style = MyAppTheme.typography.body

            )
        },
    )
}