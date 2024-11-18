package io.fairboi.mytodoapp.navigation

import androidx.navigation.NamedNavArgument


/**
 * Routes for navigation
 */
internal sealed class Screen(
    val route: String,
    val arguments: List<NamedNavArgument> = emptyList()
) {


    data object Settings : Screen("settings")

    data object TodoList : Screen("list")


}