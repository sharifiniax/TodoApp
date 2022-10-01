package org.sharifinia.todo.feature.calendar


import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.rememberScrollableState
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.sharifinia.todo.models.DayModel
import org.sharifinia.todo.models.MonthModel
import org.sharifinia.todo.models.Task
import org.sharifinia.todo.models.TimeTask
import org.sharifinia.todo.ui.theme.TodoTheme


@Composable
fun DayItem(
    dayOfWeek: String,
    dayOfMonth: String
) {

    Box(
        modifier = Modifier
            .defaultMinSize(50.dp, 60.dp)
            .background(
                MaterialTheme.colors.primaryVariant,
                RoundedCornerShape(16.dp)

            )
            .padding(4.dp),
        contentAlignment = Alignment.Center
    ) {

        Column(

            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceAround

        ) {
            Text(
                text = dayOfMonth,
                modifier = Modifier
                    .fillMaxHeight(0.4F), textAlign = TextAlign.Center
            )

            Text(
                text = dayOfWeek,
                modifier = Modifier
                    .fillMaxHeight(0.6F), textAlign = TextAlign.Center
            )

        }

    }

}

@Composable
fun BasicEvent(task: Task, modifier: Modifier = Modifier) {

    //TODO("It should be pass category param.")
    Row {

        Box(
            modifier = Modifier
                .fillMaxWidth(0.03F)
                .fillMaxHeight(1F)
                //TODO("pass color in background based on category event")
                .background(
                    if (task.ownerCategoryId % 2 == 0) Color.Blue else Color.Yellow
                )
        )


        Spacer(modifier = Modifier.width(8.dp))
        Column(Modifier.fillMaxWidth(0.97F)) {
            Spacer(modifier = Modifier.height(16.dp))
            //TODO("pass category name to Text")
            Text(text = "meeting")
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = task.title)
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text =
                "${task.timeDuration.startHour.toString()}:${task.timeDuration.startMinute.toString()}" + "-" +
                        "${task.timeDuration.endHour.toString()}:${task.timeDuration.endMinute.toString()}"
            )
            Spacer(modifier = Modifier.height(16.dp))
        }

    }


}


@Composable
fun EventLayout(
    content: @Composable () -> Unit,
    height: Int,
    modifier: Modifier = Modifier
) {
    Layout(
        modifier = modifier,
        content = content
    ) { measurables, constraints ->

        val placeables = measurables.map { measurable ->
            measurable.measure(constraints)
        }
        layout(constraints.maxWidth, height) {

            var yPosition = 0
            placeables.forEach { placeable ->
                placeable.placeRelative(x = 0, y = yPosition, 0F)
                yPosition += placeable.height
            }
        }
    }

}


@Preview(showBackground = true)
//@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun BasicEventPreview() {

    val tasks = mutableListOf<Task>()
    repeat(
        5
    ) {
        tasks.add(
            Task(
                "Amanda Ruth's $it",
                "",
                DayModel(3, 2, 3, 1990, MonthModel.Current),
                it,
                TimeTask(11, 30, 12 + it, 35),

                )
        )
    }


    LazyColumn {
        items(tasks){ item ->

            EventLayout(
                content = {
                    BasicEvent(task = item)
                },
                height = if ((item.timeDuration.endHour - item.timeDuration.startHour) * 1000 / 24 > 30) {
                    (item.timeDuration.endHour - item.timeDuration.startHour) * 10000 / 24
                } else {
                    30
                }
            )
        }
    }

//    Column(
//        modifier = Modifier.verticalScroll(rememberScrollState())
//    ) {
//        for (item in tasks) {
//
////            Spacer(modifier = Modifier.height(60.dp))
//        }
//
//
//    }


}


//@Preview(showBackground = true)
@Composable
fun DayItemPreview() {
    TodoTheme() {
        Column(
            modifier = Modifier

        ) {
            DayItem("Tue", "9")
        }
    }
}