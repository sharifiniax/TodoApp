package org.sharifinia.todo


import android.os.Bundle
import android.text.style.AlignmentSpan
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.sharifinia.todo.models.DayModel
import org.sharifinia.todo.models.MonthModel
import org.sharifinia.todo.models.Task
import org.sharifinia.todo.models.TodoCategory
import org.sharifinia.todo.ui.theme.Shapes
import org.sharifinia.todo.ui.theme.TodoTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TodoTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {

                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Composable
fun CategoryList(
    modifier: Modifier = Modifier,
    categoryList: List<TodoCategory>
) {
    LazyRow(modifier=Modifier.padding(8.dp)) {
        items(categoryList) {
            CategoryItem(item = it, modifier)
            Spacer(modifier = Modifier.width(8.dp))
        }
    }
    Spacer(modifier = Modifier.height(16.dp))
}

@Composable
fun CategoryItem(item: TodoCategory, modifier: Modifier = Modifier) {


    Column(
        modifier = Modifier
            .defaultMinSize(50.dp, 50.dp)
            .background(Color(item.color), shape = Shapes.small)


    ) {
        Box(modifier= Modifier
            .defaultMinSize(70.dp, 50.dp)
            .clip(Shapes.small)
            .background(Color(item.color))
            .border(BorderStroke(1.dp, Color.Black), RectangleShape)
            ,
            contentAlignment = Alignment.Center,

//            horizontalAlignment = Alignment.CenterHorizontally,
//            verticalArrangement = Arrangement.Center
            ) {
            Text(text = item.name)
        }

    }

}


@Composable
fun TodoList(
    modifier: Modifier = Modifier,
    todoList: List<Task>

) {
    LazyColumn {
        items(todoList) {
            TodoItem(item = it)
        }

    }
}


@Composable
fun TodoItem(item: Task, modifier: Modifier = Modifier) {

    val expand = remember {
        mutableStateOf(false)
    }

    Row(
        modifier = Modifier

            .clickable {
                expand.value = !expand.value
            }
            .padding(8.dp),
        verticalAlignment = if (expand.value) Alignment.Top else Alignment.CenterVertically

    ) {

        Checkbox(modifier = modifier
            .weight(0.1F),
            checked = item.done,
            onCheckedChange = {}
        )

        Spacer(modifier = Modifier.height(16.dp))

        Column(
            modifier = modifier
                .weight(0.9F)
        ) {
            Text(text = item.title, fontWeight = FontWeight.Bold)

            if (expand.value) {
                Spacer(modifier = Modifier.height(16.dp))
                item.description?.let {
                    Text(text = it.repeat(33))
                }
            }
        }
        Icon(
            modifier = modifier
                .weight(0.1F)
                .clickable { },
            painter = painterResource(id = R.drawable.ic_baseline_edit_24),
            contentDescription = "delete item"
        )
        Icon(
            modifier = modifier
                .weight(0.1F)
                .clickable { },
            painter = painterResource(id = R.drawable.ic_baseline_delete_outline_24),
            contentDescription = "delete item"
        )


    }


}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    val cats = mutableListOf<TodoCategory>()
    val tasks = mutableListOf<Task>()
    for (c in 1..8) {
        cats.add(
            TodoCategory(
                name = "cat$c", color = 0xFFFAFFFF
            )
        )
    }
    for (t in 1..50) {
        tasks.add(
            Task(
                "title$t",
                "Random$t",
                day = DayModel(
                    1 * t,
                    2, 11, 2022, MonthModel.Current

                ), 2
            )
        )

    }


    TodoTheme {
        Scaffold(
            floatingActionButton = {

                FloatingActionButton(onClick = { /*TODO*/ }) {
                    Text(text = "+", fontWeight = FontWeight.Bold, fontSize = 24.sp)
                }
            }
    


        ) {


            Column() {
                Spacer(modifier = Modifier.height(16.dp))
                CategoryList(categoryList = cats, modifier = Modifier.fillMaxWidth())
                Spacer(modifier = Modifier.height(16.dp))
                TodoList(todoList = tasks, modifier = Modifier.fillMaxWidth())

            }


        }

    }
}