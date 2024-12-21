package io.fairboi.domain.model.todo

import androidx.annotation.StringRes
import io.fairboi.mytodoapp.domain.R


/**
 * Represents the importance level of a to-do item.
 *
 * This enum provides three levels of importance:
 * - [NO]: Indicates a task with minimal urgency or impact.
 * - [LOW]: Represents a standard task with moderate importance.
 * - [HIGH]: Denotes a task of high urgency and significant impact.
 */
enum class TodoImportance(@StringRes val displayNameRes: Int) {
    NO( R.string.todo_importance_no),
    LOW(R.string.todo_importance_low),
    HIGH(R.string.todo_importance_high);


}