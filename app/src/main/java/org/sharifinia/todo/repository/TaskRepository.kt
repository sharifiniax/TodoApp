package org.sharifinia.todo.repository

import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.Flow
import org.sharifinia.todo.models.CategoryWithTasks
import org.sharifinia.todo.models.TaskDao
import javax.inject.Inject

//@ViewModelScoped
class TaskRepository
//@Inject
constructor(
    private val taskDao: TaskDao
){

    fun getCategoriesByTasks(): Flow<List<CategoryWithTasks>> {
        return taskDao.getCategoriesWithTasks()
    }




}