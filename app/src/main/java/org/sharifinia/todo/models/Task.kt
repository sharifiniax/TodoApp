package org.sharifinia.todo.models

import androidx.lifecycle.MutableLiveData
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import java.time.Duration


@Entity
data class Task(
    val title:String,
    val description:String?,
    @Embedded
    val day: DayModel,
    val ownerCategoryId:Int,
    @Embedded
    val timeDuration:TimeTask,
    val tag:String?=null,
    val priority:Int=1,
    var done:Boolean=false,
    val comment:String?=null
){
    @PrimaryKey(autoGenerate = true)
    var taskId:Int=0

}


data class TimeTask(
    val startHour:Int=0,
    val startMinute:Int=0,
    val endHour:Int=0,
    val endMinute:Int=0
)