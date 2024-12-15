package io.fairboi.mytodoapp.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import io.fairboi.details.TodoItemDetailsScreen
import io.fairboi.list.TodoItemsListScreen
import io.fairboi.mytodoapp.di.AppComponent
import io.fairboi.settings.SettingsScreen

@Composable
internal fun NavGraph(
    navController: NavHostController,
    appComponent: AppComponent,

    ) {
    val animationDuration = 100


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
                toDetailsScreen = {
                    navController.navigate(Screen.TodoDetails.getRoute(todoId = it)) {
                        launchSingleTop = true
                    }
                },
                toSettingsScreen = {
                    navController.navigate(Screen.Settings.route)
                }
            )
        }
        composable(
            Screen.TodoDetails.route,
            arguments = Screen.TodoDetails.arguments,
            // TODO: add deep links
            // deepLinks = Screen.TodoDetails.deepLinks,
            enterTransition = AnimatedTransitions.mainScreenEnter(animationDuration),
            exitTransition = AnimatedTransitions.mainScreenExit(animationDuration),
        ) {

            val todoItemId = it.arguments?.getString("todoId")
            TodoItemDetailsScreen(
                viewModel = remember {
                    appComponent.detailsFeatureComponent().detailsFeatureFactory().createViewModelFactory().create(
                        todoItemId
                    )
                },
                todoId = todoItemId,

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