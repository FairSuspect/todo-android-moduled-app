package io.fairboi.mytodoapp

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.compose.rememberNavController
import io.fairboi.mytodoapp.di.AppComponent
import io.fairboi.mytodoapp.navigation.NavGraph
import io.fairboi.theme.custom.MyTodoAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val appComponent = (application as TodoApplication).appComponent

        // Пока будет такой вариант, в будущем добавлю фичу редактирования и
        // по такому интенту будет открываться полноценное редактирование, чтобы пользователь
        // мог отредактировать текст задачи.
        if (intent?.action == Intent.ACTION_SEND && intent?.type == "text/plain") {
            val sharedText = intent?.getStringExtra(Intent.EXTRA_TEXT)
            if (sharedText != null) {
                // Создание задачи из sharedText
                appComponent.listFeatureComponent().listViewModel.addTodoFromText(sharedText)
            }
        }

        enableEdgeToEdge()
        setContent {
            TodoApp(appComponent)
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


     MyTodoAppTheme(
       theme = settings.theme
    ) {

        NavGraph(
            navController = navController,
            appComponent = appComponent,

            )
    }
}



