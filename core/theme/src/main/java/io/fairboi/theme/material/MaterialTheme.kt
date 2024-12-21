package io.fairboi.theme.material

import android.util.Log
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
@Composable
fun MyTodoAppMaterialTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit,
) {
    val colors = if (darkTheme) darkColorSchemeImpl else lightColorSchemeImpl
    Log.d("MyTodoAppMaterialTheme", "isDarkTheme: $darkTheme")
    MaterialTheme(
        colorScheme = colors,
        typography = typography,
    ) {
        content()
    }
}