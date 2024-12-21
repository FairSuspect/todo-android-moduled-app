package io.fairboi.mytodoapp.navigation

import androidx.navigation.NamedNavArgument
import androidx.navigation.navArgument
import io.fairboi.domain.model.todo.TodoId


/**
 * Routes for navigation
 */
internal sealed class Screen(
    val route: String,
    val arguments: List<NamedNavArgument> = emptyList()
) {


    data object Settings : Screen("settings")

    data object TodoList : Screen("todos")

    data object TodoDetails :
        Screen("todos/{todoId}", listOf(navArgument("todoId") { nullable = true })) {
        fun getRoute(todoId: TodoId?) = "todos/$todoId"

    }


}