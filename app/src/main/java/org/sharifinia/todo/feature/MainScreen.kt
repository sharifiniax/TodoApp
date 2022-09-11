package org.sharifinia.todo.feature.mainscreen


import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import org.sharifinia.todo.feature.Projects
import org.sharifinia.todo.feature.ShowTasksList
import org.sharifinia.todo.models.*
import org.sharifinia.todo.ui.theme.TodoTheme
import kotlin.random.Random

@Composable
fun MainScreen(){





}

@Preview
@Preview(showBackground = true)
@Composable
fun MainPreview() {
    val cats = mutableListOf<TodoCategory>()
    val tasks = mutableListOf<Task>()
    for (c in 1..3) {
        val cat = TodoCategory(
            name = "Inbox$c", color = EColor.values()[c]
        )
        cat.categoryId = c
        cats.add(
            cat
        )
    }
    for (t in 1..50) {
        tasks.add(
            Task(
                ownerCategoryId = Random.nextInt(4),
                title = "title$t",
                description = "Random$t",
                day = DayModel(
                    1 * t,
                    2, 11, 2022, MonthModel.Current
                ),

                done = Random.nextBoolean(),
                timeDuration = TimeTask(11, 30, 12, 30)
            )
        )

    }
    val catTasks = mutableListOf<CategoryWithTasks>()
    for (i in 1..3) {
        val tasksWithGroup = mutableListOf<Task>()
        tasks.filter {
            it.ownerCategoryId == i
        }.forEach {
            tasksWithGroup.add(it)
        }

        catTasks.add(
            CategoryWithTasks(cats[i - 1], tasksWithGroup)
        )

    }

    TodoTheme {
        Column() {
            Statistics(catTasks)
            Projects(tasks = tasks, cats = cats)
            ShowTasksList(tasks = tasks)
        }
    }
}

