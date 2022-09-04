package org.sharifinia.todo.models

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Task::class,
    TodoCategory::class], version = 1)
abstract class TodoDatabase : RoomDatabase(){

    abstract fun TaskDao(): TaskDao
    abstract fun TodoCategoryDao():CategoryDao

}