package org.sharifinia.todo.models

import androidx.compose.ui.graphics.Color
import androidx.room.Entity
import androidx.room.PrimaryKey



@Entity
data class TodoCategory(
    val name:String = "Inbox",
    val color: Long = 0xFFFFFFFF
){

    @PrimaryKey(autoGenerate = true)
    var categoryId:Int=0

}





