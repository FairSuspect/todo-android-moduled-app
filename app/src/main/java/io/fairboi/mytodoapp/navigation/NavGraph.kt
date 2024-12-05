package io.fairboi.mytodoapp.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import io.fairboi.list.TodoItemsListScreen
import io.fairboi.mytodoapp.di.AppComponent
import io.fairboi.settings.SettingsScreen

@Composable
internal fun NavGraph(
    navController: NavHostController,
    appComponent: AppComponent,

    ) {
    val animationDuration = 300


    NavHost(navController = navController, startDestination = Screen.TodoList.route) {
        composable(
            Screen.TodoList.route,
            enterTransition = AnimatedTransitions.mainScreenEnter(animationDuration),
            exitTransition = AnimatedTransitions.mainScreenExit(animationDuration),
        ) {
            TodoItemsListScreen(
                viewModel = remember {
                    appComponent.listFeatureComponent().listViewModel
                },
                toSettingsScreen = {
                    navController.navigate(Screen.Settings.route)
                }
            )
        }
        composable(
            Screen.Settings.route,
            enterTransition = AnimatedTransitions.secondScreenEnter(animationDuration),
            exitTransition = AnimatedTransitions.secondScreenExit(animationDuration)
        ) {
            val viewModel = remember {
                appComponent.settingsFeatureComponent().settingsViewModel
            }
            SettingsScreen(
                viewModel = viewModel,
                onBack = {
                    navController.popBackStack()
                }
            )

        }

    }
}