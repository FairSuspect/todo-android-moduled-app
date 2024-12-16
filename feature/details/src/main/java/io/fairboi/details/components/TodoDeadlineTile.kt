package io.fairboi.details.components

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import io.fairboi.details.R
import io.fairboi.ui.previews.DefaultPreview
import io.fairboi.ui.previews.LanguagePreview
import io.fairboi.ui.previews.LayoutDirectionPreview
import io.fairboi.ui.previews.ThemePreview
import java.time.Instant
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.TimeZone

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun TodoDeadLineTile(
    deadline: LocalDateTime?,
    onChanged: (deadline: LocalDateTime?) -> Unit,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    var deadline by remember { mutableStateOf(deadline) }
    var showDatePicker by remember { mutableStateOf(false) }
    val datePickerState = rememberDatePickerState(
        initialSelectedDateMillis = deadline?.atZone(TimeZone.getDefault().toZoneId())?.toInstant()
            ?.toEpochMilli()
    )

    fun onSwitchChanged(checked: Boolean) {
        if (checked) {
            showDatePicker = true
        } else {
            deadline = null
            datePickerState.selectedDateMillis = null
            onChanged(null)
        }
    }

    val hasDeadline = deadline != null

    if (showDatePicker) {
        DatePickerDialog(
            onDismissRequest = { showDatePicker = false },
            confirmButton = {
                TextButton(onClick = {
                    showDatePicker = false
                    deadline = datePickerState.selectedDateMillis?.let { millis ->
                        LocalDateTime.ofInstant(
                            Instant.ofEpochMilli(millis),
                            TimeZone.getDefault().toZoneId()
                        )
                    }
                    onChanged(deadline)
                }) {
                    Text("OK")
                }
            },
            dismissButton = {
                TextButton(onClick = { showDatePicker = false }) {
                    Text("Cancel")
                }
            }
        ) {
            DatePicker(state = datePickerState)
        }

    }
    ListItem(
        modifier = modifier
            .fillMaxWidth()
            .clickable(
                onClick = {
                    Log.d("TodoDeadLineTile", "Switch clicked")
                    val newChecked = !hasDeadline
                    onSwitchChanged(newChecked)
                }
            ),
        headlineContent = {
            Column {
                Text(
                    text = context.getString(R.string.deadline)
                )
                if (hasDeadline) {
                    val formattedDateline =
                        deadline!!.format(DateTimeFormatter.ofPattern("dd MMMM yyyy"))
                    Text(
                        text = formattedDateline
                    )
                }
            }
        },
        trailingContent = {
            Switch(
                checked = hasDeadline,
                onCheckedChange = { checked ->
                    onSwitchChanged(checked)
                },
                modifier = modifier

            )
        }
    )


}

@DefaultPreview
@ThemePreview
@LanguagePreview
@LayoutDirectionPreview
@Composable
private fun TodoDeadlineTilePreview(
    @PreviewParameter(TodoDeadlineProvider::class) deadline: LocalDateTime?
) {
    MaterialTheme {
        Box(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.surface)
                .padding(8.dp)
        ) {

            TodoDeadLineTile(
                deadline = deadline,
                onChanged = {}
            )
        }
    }
}

class TodoDeadlineProvider : PreviewParameterProvider<LocalDateTime?> {
    override val values: Sequence<LocalDateTime?> = sequenceOf(
        null,
        LocalDateTime.now(),
    )

}

