package io.fairboi.ui.previews

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import io.fairboi.domain.model.todo.TodoImportance
import io.fairboi.domain.model.todo.TodoItem
import java.time.Instant
import java.time.LocalDateTime
import java.time.temporal.ChronoUnit

class TextPreviewParameterProvider : PreviewParameterProvider<String> {
    override val values: Sequence<String>
        get() = sequenceOf(
            "Test text",
            "Medium size text with a long of a sentence",
            "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nulla mi purus, semper eget tortor id, posuere placerat nunc. Sed ut mi nunc. Pellentesque placerat vulputate rhoncus. Sed id odio augue. Vestibulum quis ligula rutrum, maximus ex eget, pulvinar velit. Mauris tellus nunc, egestas at metus eget, congue interdum dui. Quisque faucibus, leo vel ultrices tempus, justo mi fermentum dolor, non tincidunt augue mauris sit amet quam. Duis ut libero ut metus luctus pharetra ultricies sit amet erat. Maecenas a nunc quis enim vehicula fringilla. Sed porta sapien id ipsum fermentum convallis. In hac habitasse platea dictumst. Duis cursus ex metus, et faucibus diam pulvinar sed. Praesent mattis mauris sem. Sed cursus erat eu nulla sagittis mollis. Morbi libero nibh, imperdiet eget consectetur at, dignissim vitae nulla."
        )
}

class TodoItemPreviewParameterProvider :
    PreviewParameterProvider<TodoItem> {
    override val values: Sequence<TodoItem>
        get() {
            val currentInstant = Instant.now()
            val currentDate = LocalDateTime.now()

            return sequenceOf(
                *Array(5) {
                    TodoItem(
                        id = it.toString(),
                        text = "Дело $it ".repeat(it * 5 + 1),
                        importance = TodoImportance.entries[it % 3],
                        deadline = if (it % 3 == 0)
                            null
                        else currentDate.plusDays((-100L..100L).random()),
                        done = it % 4 == 0,
                        changedAt = currentInstant.minus((100L..1000L).random(), ChronoUnit.DAYS),
                        createdAt = currentInstant.minus((100L..1000L).random(), ChronoUnit.DAYS),
                    )
                }
            )
        }
}

class BooleanPreviewParameterProvider : PreviewParameterProvider<Boolean> {
    override val values: Sequence<Boolean>
        get() = sequenceOf(true, false)
}