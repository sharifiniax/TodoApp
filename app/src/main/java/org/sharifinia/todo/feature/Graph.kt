package org.sharifinia.todo.features

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.sharifinia.todo.models.*
import org.sharifinia.todo.ui.theme.TodoTheme
import kotlin.random.Random
import kotlin.random.nextUInt

@Composable
fun Statistics(cats: List<CategoryWithTasks>) {

Card(modifier = Modifier.fillMaxWidth(),

    ) {
    Column(modifier = Modifier.padding(8.dp)) {

        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Graph(cats)
            ListCategory(
                cats
            )

        }


    }

}


}

@Composable
fun ListCategory(cats: List<CategoryWithTasks>) {

    Row {
        Column() {
            for (item in cats) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Canvas(
                        modifier = Modifier
                            .padding(start = 8.dp, end = 8.dp)
                            .size(6.dp)
                    ) {
                        drawCircle(Color.Black)
                    }

                    Text(text = item.category.name)
                }


            }
        }
        Spacer(modifier = Modifier.width(8.dp))
        Column() {
            for (item in cats) {

                val done = item.tasks.filter { it.done }.size
                Text(text = "${done * 100 / item.tasks.size}%")
            }
        }
    }


}

@Composable
fun Graph(
    cats: List<CategoryWithTasks>
) {
    //TODO("create graph base input")
    val listArc = mutableListOf<Arc>()

    for ((index, item) in cats.take(3).withIndex()) {
        val done = item.tasks.filter { it.done }.size
        val percentage = done * 100F / item.tasks.size
        listArc.add(Arc(EColor.convertColor(item.category.color), percentage, (index+1).toFloat()))
    }
    CircleGraph(
        listArc
    )
}

class Arc(val color: Color, val percentage: Float, val level: Float)




@Composable
fun CircleGraph(arcs: List<Arc>) {


    Canvas(
        modifier = Modifier
            .size(size = 200.dp)
    ) {

        for (item in arcs) {

            drawCircle(
                color = Color.LightGray,
                radius = 0.5F * item.level * size.minDimension / 4F,
                center = Offset(
                    size.minDimension / 2F,
                    size.minDimension / 2F
                ),
                style = Stroke(10F)
            )
            drawArc(
                color = item.color,
                -90F, 360F * item.percentage / 100F, false,
                topLeft = Offset(
                    x = (size.minDimension / 2F) - 0.5F * item.level * size.minDimension / 4F,
                    y = (size.minDimension / 2F) - 0.5F * item.level * size.minDimension / 4F
                ),
                style = Stroke(30F, cap = StrokeCap.Round),
                size = Size(
                    item.level * size.minDimension / 4F,
                    item.level * size.minDimension / 4F
                )
            )
        }
    }


}

@Preview
@Preview(showBackground = true)
@Composable
fun GPreview() {
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
        }
    }
}

