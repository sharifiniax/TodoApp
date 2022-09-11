package org.sharifinia.todo.feature

import android.content.res.Configuration
import android.graphics.Color.parseColor
import android.graphics.drawable.shapes.OvalShape
import android.text.method.SingleLineTransformationMethod
import android.view.TextureView
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.LazyScopeMarker
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.MaterialTheme.colors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawStyle
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.graphics.toColor
import org.sharifinia.todo.models.*
import org.sharifinia.todo.ui.theme.Shapes
import org.sharifinia.todo.ui.theme.TodoTheme
import kotlin.random.Random


@Composable
fun Projects(
    tasks: List<Task>,
    cats: List<TodoCategory>
) {


    Row {
        Column(modifier = Modifier.weight(1F)) {
            tasks[0].let { item ->
                Card(
                    modifier = Modifier
                        .padding(2.dp)
                        .defaultMinSize(150.dp, 200.dp)
                    , elevation = 5.dp, shape = MaterialTheme.shapes.small,
                    backgroundColor = MaterialTheme.colors.background
                ) {
                    val cat = cats.first { item.ownerCategoryId == it.categoryId }
                    val color = cat.color
                    val catName = cat.name
                    val totalTasks = tasks.filter { it.ownerCategoryId == cat.categoryId }.size
                    val doneTasks = tasks.filter { it.ownerCategoryId == cat.categoryId }.filter { it.done }.size
                    val percentage = doneTasks*100F/totalTasks
                    val arc = percentage *360F/100F
                    Column(
                        Modifier
                            .padding(16.dp)
                            .size(150.dp, 220.dp)
                    ) {
                        Canvas(
                            modifier = Modifier
                                .size(45.dp)
                        ) {
                            val radius = size.minDimension / 2
                            val rate = 0.4F
                            drawCircle(
                                color = EColor.convertColor(color,500) ,
                                radius = radius,
                                center = Offset(x = radius, y = radius),
                                style = Stroke(5F)
                            )
                            drawArc(
                                color = EColor.convertColor(color,200),
                                -90F, arc,
                                topLeft = Offset(
                                    x = rate * radius / 2,
                                    y = rate * radius / 2
                                ),
                                useCenter = true,
                                size = Size(
                                    (2 - rate) * radius,
                                    (2 - rate) * radius
                                )

                            )


                        }

                        Spacer(modifier = Modifier.height(16.dp))

                        Text(text = catName,
                            style = MaterialTheme.typography.body1,
                            color = EColor.convertColor(color,200)
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        //TODO('Text base title in task element')
                        Text(text = "item title in ruth package.",
                            style = MaterialTheme.typography.h5,
                            maxLines = 2, overflow = TextOverflow.Ellipsis)
                        Spacer(modifier = Modifier.height(16.dp))
                        Button(onClick = { /*TODO('create base calendar')*/ }, colors = ButtonDefaults.buttonColors(backgroundColor = EColor.convertColor(color))) {
                            Text(text = "TODAY")
                        }

                    }

                    Spacer(modifier = Modifier.width(8.dp))
                }
                Spacer(modifier = Modifier.width(16.dp))
            }
        }
        Column(modifier = Modifier.weight(1F)) {
            tasks[1].let { item ->
                Card(
                    modifier = Modifier
                        .padding(2.dp)
                        .defaultMinSize(150.dp, 200.dp),
                    elevation = 5.dp, shape = MaterialTheme.shapes.small,
                    backgroundColor = MaterialTheme.colors.background

                ) {
                    val cat = cats.first { item.ownerCategoryId == it.categoryId }
                    val color = cat.color
                    val catName = cat.name
                    val totalTasks = tasks.filter { it.ownerCategoryId == cat.categoryId }.size
                    val doneTasks = tasks.filter { it.ownerCategoryId == cat.categoryId }.filter { it.done }.size
                    val percentage = doneTasks*100F/totalTasks
                    val arc = percentage *360F/100F
                    Column(
                        Modifier
                            .padding(16.dp)
                            .size(150.dp, 220.dp)
                    ) {
                        Canvas(
                            modifier = Modifier
                                .size(45.dp)
                        ) {
                            val radius = size.minDimension / 2
                            val rate = 0.4F
                            drawCircle(
                                color = EColor.convertColor(color,500) ,
                                radius = radius,
                                center = Offset(x = radius, y = radius),
                                style = Stroke(5F)
                            )
                            drawArc(
                                color = EColor.convertColor(color,200),
                                -90F, arc,
                                topLeft = Offset(
                                    x = rate * radius / 2,
                                    y = rate * radius / 2
                                ),
                                useCenter = true,
                                size = Size(
                                    (2 - rate) * radius,
                                    (2 - rate) * radius
                                )

                            )


                        }

                        Spacer(modifier = Modifier.height(16.dp))

                        Text(text = catName,
                            style = MaterialTheme.typography.body1,
                            color = EColor.convertColor(color,200)
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        //TODO('Text base title in task element')
                        Text(text = "item title in ruth package.",
                            style = MaterialTheme.typography.h5,
                            maxLines = 2, overflow = TextOverflow.Ellipsis)
                        Spacer(modifier = Modifier.height(16.dp))
                        Button(onClick = { /*TODO('create base calendar')*/ }, colors = ButtonDefaults.buttonColors(backgroundColor = EColor.convertColor(color))) {
                            Text(text = "TODAY")
                        }

                    }

                    Spacer(modifier = Modifier.width(8.dp))
                }
                Spacer(modifier = Modifier.width(16.dp))
            }
        }




    }


}


@Preview(showBackground = true)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun JPreview() {
    val cats = mutableListOf<TodoCategory>()
    val tasks = mutableListOf<Task>()

    for (c in 1..8) {
        val cat = TodoCategory(
            name = "meeting", color = EColor.Brown
        )
        cat.categoryId = c
        cats.add(
            cat
        )
    }
    for (t in 1..70) {
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
        Column() {
            Projects(tasks = tasks, cats = cats)
        }
    }

}