/* While this template provides a good starting point for using Wear Compose, you can always
 * take a look at https://github.com/android/wear-os-samples/tree/main/ComposeStarter to find the
 * most up to date changes to the libraries and their usages.
 */

package io.fairboi.weartodo.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.wear.compose.material.MaterialTheme
import androidx.wear.compose.material.TimeText
import io.fairboi.domain.model.ThemeSettings
import io.fairboi.theme.custom.MyTodoAppTheme
import io.fairboi.ui.previews.TodoItemPreviewParameterProvider
import io.fairboi.weartodo.presentation.components.TodoItemsList
import io.fairboi.weartodo.presentation.previews.DefaultWearPreview

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()

        super.onCreate(savedInstanceState)

        setTheme(android.R.style.Theme_DeviceDefault)

        setContent {
            WearApp()
        }
    }
}

@Composable
fun WearApp() {
    MyTodoAppTheme(theme = ThemeSettings.Dark) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colors.background),
            contentAlignment = Alignment.Center
        ) {
            TimeText()
            TodoItemsList(
                TodoItemPreviewParameterProvider().values.toList(),
            )
        }
    }
}

@DefaultWearPreview
@Composable
fun DefaultPreview() {
    WearApp()
}

