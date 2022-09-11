package org.sharifinia.todo.feature

import android.content.res.Configuration
import androidx.compose.foundation.BorderStroke

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*

import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip


import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.sharifinia.todo.models.DayModel
import org.sharifinia.todo.models.MonthModel
import org.sharifinia.todo.models.Task
import org.sharifinia.todo.models.TimeTask
import org.sharifinia.todo.ui.theme.Shapes
import org.sharifinia.todo.ui.theme.TodoTheme
import kotlin.random.Random

@Composable
fun ShowTasksList(tasks: List<Task>) {

    LazyColumn(modifier = Modifier
        .fillMaxWidth()
        .clip(Shapes.small)) {
        items(tasks) {
            Card(
                modifier = Modifier.padding(8.dp)
                    .requiredHeight(50.dp)

                ,
                elevation = 5.dp,
                shape = MaterialTheme.shapes.medium
            ) {
                ItemTaskList(task = it)
            }

//            Spacer(modifier = Modifier.height(4.dp))
        }

    }


}

@Composable
fun ItemTaskList(task: Task) {


    Row (verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()
        ){
        //TODO(set unchanged check)
        Check()
        Spacer(modifier = Modifier.width(8.dp))
        Text(text = task.title)
    }

}

@Composable
fun Check() {
    Box(
        modifier = Modifier
            .clip(CircleShape)
            .size(30.dp)
            .padding(5.dp)
            .border(BorderStroke(1.dp, MaterialTheme.colors.onSecondary), shape = CircleShape)
            ,
        contentAlignment = Alignment.Center
    ) {
//        Icon(imageVector = Icons.Default.Check, contentDescription = "")
    }
}


@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Preview(showBackground = true)
@Composable
fun TaskPreview() {
    val tasks = mutableListOf<Task>()

    for (t in 1..7) {
        tasks.add(
            Task(
                "title$t",
                "Random$t",
                day = DayModel(
                    1 * t,
                    2, 11, 2022, MonthModel.Current

                ),
                ownerCategoryId = t.mod(8) + 1,
                TimeTask(11, 30, 12, 30),
                done = Random.nextBoolean()

            )
        )

    }
    TodoTheme() {
        ShowTasksList(tasks)
    }




}