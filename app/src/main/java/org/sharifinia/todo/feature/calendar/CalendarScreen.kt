package org.sharifinia.todo.feature.calendar

import android.content.res.Configuration
import android.graphics.drawable.shapes.OvalShape
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.materialIcon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.sharifinia.todo.models.DayModel
import org.sharifinia.todo.models.MonthModel
import org.sharifinia.todo.ui.theme.Shapes
import org.sharifinia.todo.ui.theme.TodoTheme
import org.sharifinia.todo.utils.Utils


@Composable
fun DayItem(dayOfMonth :Int,dayOfWeek:String){

    Box(
        modifier = Modifier
            .defaultMinSize(48.dp, 32.dp)
            .clip(RoundedCornerShape(25))
            .background(MaterialTheme.colors.primaryVariant),
        contentAlignment = Alignment.Center



    ) {
        Column(
            modifier = Modifier.padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "$dayOfMonth",
            style = TextStyle(fontWeight = FontWeight.Bold)
                )
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "$dayOfWeek")

        }

    }
    
    
}

@Composable
fun ListOfTheDays(days: List<DayModel>){
    
    LazyRow(){ 
        items(days){
            item->
            DayItem(dayOfMonth =item.dayOfMonth , 
                dayOfWeek =Utils.getDayWeekBrief(item.dayOfWeek))
            Spacer(modifier = Modifier.width(16.dp))
            
            
        }
    }
    
    
    
}

@Composable
fun HourLine(hourMode: Int = 24) {

    LazyColumn(){
        items(24){
            Row {
                Spacer(modifier = Modifier.width(24.dp))
                Text(text = it.toString())
                Spacer(modifier = Modifier.width(24.dp))
                Divider(color=MaterialTheme.colors.primaryVariant, thickness = 1.dp)
                Spacer(modifier = Modifier.height(64.dp))
            }

        }
    }



}



@Preview(showBackground = true)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun calendarPreview(){

    val days= mutableListOf<DayModel>()
    for (day in 1..31)
        days.add(DayModel(day.mod(7),day,2,1990,MonthModel.Current))

    TodoTheme {

        Column() {
            ListOfTheDays(days = days)
            Spacer(modifier = Modifier.height(48.dp))

            HourLine()
        }



        
    }


}