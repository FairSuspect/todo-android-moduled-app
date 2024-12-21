package io.fairboi.list.components

import android.Manifest
import android.content.Context
import android.os.Build
import android.os.VibrationEffect
import android.os.Vibrator
import android.widget.Toast
import androidx.annotation.RequiresPermission
import androidx.compose.material3.SwipeToDismissBox
import androidx.compose.material3.SwipeToDismissBoxValue.EndToStart
import androidx.compose.material3.SwipeToDismissBoxValue.Settled
import androidx.compose.material3.SwipeToDismissBoxValue.StartToEnd
import androidx.compose.material3.rememberSwipeToDismissBoxState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.ContextCompat.getSystemService
import io.fairboi.domain.model.todo.TodoId
import io.fairboi.domain.model.todo.TodoItem

@RequiresPermission(Manifest.permission.VIBRATE)
@Composable
fun DismissibleTotoItemTile(
    modifier: Modifier = Modifier,
    onRemove: (itemId: TodoId) -> Unit = {},
    todoItem: TodoItem,
    onClick: () -> Unit = {},
    onCheckedChange: (Boolean) -> Unit = {},
) {
    val context = LocalContext.current

    val dismissState = rememberSwipeToDismissBoxState(
        confirmValueChange = {
            when (it) {
                StartToEnd -> {
                    onRemove(todoItem.id)
                    Toast.makeText(context, "Item deleted", Toast.LENGTH_SHORT).show()
                }

                EndToStart -> {
                    onCheckedChange(true)
                    Toast.makeText(context, "Item checked", Toast.LENGTH_SHORT).show()

                    shortVibrate(context)
                }

                Settled -> {}
            }
            return@rememberSwipeToDismissBoxState false
        },
        // positional threshold of 25%
        positionalThreshold = { it * .25f }

    )

    SwipeToDismissBox(
        state = dismissState,
        backgroundContent = { DismissBackground(dismissState) },
        modifier = modifier,
    ) {
        TodoItemTile(
            todoItem = todoItem,
            onClick = onClick,
            onCheckedChange = onCheckedChange,
            modifier = modifier,
        )
    }
}

@RequiresPermission(Manifest.permission.VIBRATE)
private fun shortVibrate(context: Context) {
    val vibrator = getSystemService(context, Vibrator::class.java) as Vibrator
    if (Build.VERSION.SDK_INT >= 29) {
        vibrator.vibrate(VibrationEffect.createOneShot(50, VibrationEffect.EFFECT_HEAVY_CLICK))
    } else {
        vibrator.vibrate(50)
    }
}
