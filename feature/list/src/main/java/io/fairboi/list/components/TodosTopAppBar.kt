package io.fairboi.list.components

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.Visibility
import androidx.compose.material.icons.outlined.VisibilityOff
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.lerp
import androidx.compose.ui.unit.max
import androidx.compose.ui.unit.sp
import io.fairboi.list.TodoItemsListViewModel
import io.fairboi.list.TodoItemsUiState

private const val TAG = "TodosAppBar"

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TodosAppBar(
    viewModel: TodoItemsListViewModel,
    modifier: Modifier = Modifier,
    scrollState: LazyListState = rememberLazyListState(),
    toSettingsScreen: () -> Unit,
) {
    val uiState by viewModel.uiState.collectAsState()
    val filter = uiState.filterState
    val theme = MaterialTheme
    val scrollOffset = remember {
        derivedStateOf {
            val itemHeight = 150 // Assuming each item has a height of 150dp
            val itemsBeforeFirstVisible = scrollState.firstVisibleItemIndex
            (itemsBeforeFirstVisible * itemHeight) + scrollState.firstVisibleItemScrollOffset
        }
    }
    val scrollOffsetPx = scrollOffset.value.toFloat()
    val baseTopPadding = with(LocalDensity.current) { 30.dp.toPx() }
    val baseStartPadding = with(LocalDensity.current) { 10.dp.toPx() }
    val topPadding = calculatePadding(baseTopPadding, scrollOffsetPx, 0.dp, multiplier = 1f)
    val startPadding = calculatePadding(baseStartPadding, scrollOffsetPx, 0.dp, multiplier = 0.5f)
    val minRatio = 0f
    val ratio = 1f - (scrollOffsetPx / 50f)
    val effectiveRatio = if (ratio < minRatio) 0f else ratio
    val textSize = lerp(24.sp, 36.sp, effectiveRatio)


    TopAppBar(
        modifier = modifier
            .padding(
                start = startPadding,
                top = topPadding,
            ),


        title = {
            Column {
                Text(
                    "Todo Items",
                    fontSize = textSize,
                    overflow = TextOverflow.Ellipsis
                )
                if (uiState.listState is TodoItemsUiState.ListState.Loaded && effectiveRatio > 0f)
                    Text(
                        "Done – ${(uiState.listState as TodoItemsUiState.ListState.Loaded).completedCount}",
                        style = theme.typography.labelMedium,
                        //opacity 1f -> 0f
                        color = theme.colorScheme.onSurface.copy(alpha = effectiveRatio)
                    )
            }
        },
        actions = {
            IconButton(onClick = {
                val newFilter = when (filter) {
                    TodoItemsUiState.FilterState.ALL -> TodoItemsUiState.FilterState.NOT_COMPLETED
                    TodoItemsUiState.FilterState.NOT_COMPLETED -> TodoItemsUiState.FilterState.ALL
                }
                Log.d(TAG, "Filter toggled (${filter.name} -> ${newFilter.name})")
                viewModel.onFilterChanged(
                    newFilter
                )
            }) {
                Icon(
                    if (filter == TodoItemsUiState.FilterState.ALL) Icons.Outlined.Visibility else Icons.Outlined.VisibilityOff,
                    contentDescription = "Toggle visibility"
                )
            }


            IconButton(onClick = toSettingsScreen) {
                Icon(Icons.Filled.Settings, contentDescription = "Settings")
            }
        }
    )
}

private fun calculatePadding(
    basePadding: Float,
    scrollOffset: Float,
    minPadding: Dp = 0.dp,
    multiplier: Float = 1f,
): Dp {

    val calculatedPadding = (basePadding - scrollOffset * multiplier).dp
    return max(calculatedPadding, minPadding)

}