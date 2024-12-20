package io.fairboi.details.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import io.fairboi.theme.custom.MyAppTheme
import io.fairboi.ui.previews.DefaultPreview
import io.fairboi.ui.previews.ItemPreviewTemplate
import io.fairboi.ui.previews.LanguagePreview
import io.fairboi.ui.previews.LayoutDirectionPreview
import io.fairboi.ui.previews.ThemePreview

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailedTopBar(
    onBack: () -> Unit,
    onSave: () -> Unit,
    modifier: Modifier = Modifier
) {
    TopAppBar(
        title = {},
        navigationIcon = {
            IconButton(
                onClick = onBack,
                modifier = modifier
            ) {
                Icon(Icons.Default.Close, contentDescription = "Close")
            }
        },
        actions = {
            TextButton(
                onClick = onSave,
            ) {
                Text("Сохранить".uppercase())
            }

        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MyAppTheme.colors.primaryBack,
            navigationIconContentColor = MyAppTheme.colors.primary,
            actionIconContentColor = MyAppTheme.colors.blue
        )
    )
}

@DefaultPreview
@ThemePreview
@LanguagePreview
@LayoutDirectionPreview
@Composable
private fun DetailedTopBarPreview() {
    ItemPreviewTemplate{
    DetailedTopBar(
        onBack = {},
        onSave = {},
        modifier = Modifier.padding()
    )

    }



}