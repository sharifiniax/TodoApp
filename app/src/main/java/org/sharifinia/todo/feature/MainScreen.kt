package org.sharifinia.todo.feature


import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.sharifinia.todo.models.*
import org.sharifinia.todo.ui.theme.TodoTheme
import kotlin.random.Random

@Composable
fun MainScreen(categoriesWithTasks:List<CategoryWithTasks>,
               categories:List<TodoCategory>,
               tasks:List<Task>

){
    TodoTheme {
        Column(Modifier.padding(16.dp)) {
            Statistics(categoriesWithTasks)
//            GPreview()
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = "Projects",style=MaterialTheme.typography.h5, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(16.dp))
            Projects(tasks = tasks, cats = categories)
//            JPreview()
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = "Tasks",style=MaterialTheme.typography.h5, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(16.dp))
            ShowTasksList(tasks = tasks)
//            TaskPreview()
        }
    }




}

@Preview(showBackground = true)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
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
        Column(Modifier.padding(16.dp)) {
            Statistics(catTasks)
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = "Projects",style=MaterialTheme.typography.h5, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(16.dp))
            Projects(tasks = tasks, cats = cats)
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = "Tasks",style=MaterialTheme.typography.h5, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(16.dp))
            ShowTasksList(tasks = tasks)
        }
    }
}

