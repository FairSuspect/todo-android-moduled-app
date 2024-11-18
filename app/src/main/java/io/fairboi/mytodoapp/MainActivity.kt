package io.fairboi.mytodoapp

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.navigation.compose.rememberNavController
import io.fairboi.domain.model.ThemeSettings
import io.fairboi.mytodoapp.di.AppComponent
import io.fairboi.mytodoapp.navigation.NavGraph
import io.fairboi.mytodoapp.ui.theme.MyTodoAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TodoApp((application as TodoApplication).appComponent)
        }
    }
}

@Composable
internal fun TodoApp(
    appComponent: AppComponent,
) {
    val navController = rememberNavController()
    val settings by
        appComponent.settingsRepository().getSettings().collectAsState()


    Log.d("TodoApp", "settings: $settings")
    MyTodoAppTheme(
       themeSettings = settings.theme
    ) {

        NavGraph(
            navController = navController,
            appComponent = appComponent,

            )
    }
}



