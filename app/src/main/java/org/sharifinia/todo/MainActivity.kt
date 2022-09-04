package org.sharifinia.todo

import android.os.Bundle
import android.widget.CheckBox
import android.widget.ImageButton
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
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
    LazyRow() {
        items(categoryList) {
            CategoryItem(item = it, modifier)

        }
    }
    Spacer(modifier = Modifier.height(8.dp))
}

@Composable
fun CategoryItem(item: TodoCategory, modifier: Modifier = Modifier) {


    Column(modifier = Modifier.background(Color(item.color), shape = Shapes.small)) {
        Text(text = item.name)
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
    Column(modifier=Modifier.clickable {
        expand.value=!expand.value
    }) {

        Row(modifier = Modifier) {

            Checkbox(checked = item.done, onCheckedChange = {})
            Text(text = item.title)
            Icon(
                painter = painterResource(id = R.drawable.ic_baseline_delete_outline_24),
                contentDescription = "delete item"
            )


        }

        Row() {
            if (expand.value) {
                item.description?.let {
                    Text(text = it)
                }
            }
        }
    }




}


@Composable
fun ActionButton(modifier: Modifier = Modifier) {
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = {

            }) {

            }
        },
        isFloatingActionButtonDocked = true,
        //floatingActionButtonPosition = FabPosition.End

    ) {

    }

}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    val cats = mutableListOf<TodoCategory>()
    val tasks = mutableListOf<Task>()
    for (c in 1..8){
        cats.add(
        TodoCategory(
            name = "cat$c", 0xFFFFFFFF
        )
        )
    }
    for (t in 1..50){
        tasks.add(
        Task("title$t",
            "Random$t",
            day = DayModel(1*t,
                2,11,2022,MonthModel.Current

                ),2
            ))

    }


    TodoTheme {
        CategoryList(categoryList =cats )
        TodoList(todoList = tasks)
    }
}