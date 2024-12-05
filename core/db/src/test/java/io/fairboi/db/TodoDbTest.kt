package io.fairboi.db

import java.time.Instant
import java.time.LocalDateTime
import org.junit.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class TodoDbTest {
    @Test
    fun transform(){
        val now = LocalDateTime.now()
        val nowInstant = Instant.now()
        val dbItem = TodoDbItem(
        id = "1", text = "text",
        deadline = now,
        done = false,
        createdAt = nowInstant,
        changedAt = nowInstant,
        )

        val item = dbItem.toTodoItem()

        assert(item.id == dbItem.id)
        assert(item.text == dbItem.text)
        assert(item.deadline == dbItem.deadline)
        assert(item.done == dbItem.done)
        assert(item.createdAt == dbItem.createdAt)
        assert(item.changedAt == dbItem.changedAt)
        assert(item.deadline == now)

    }
}