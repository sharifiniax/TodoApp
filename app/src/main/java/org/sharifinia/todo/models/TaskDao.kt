package org.sharifinia.todo.models

import androidx.room.*
import kotlinx.coroutines.flow.Flow


@Dao
interface TaskDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE )
    suspend fun insert(task: Task)

    @Query("SELECT * FROM Task")
    fun getAll():Flow<List<Task>>

    @Query("SELECT * FROM Task ts WHERE ts.done == 0 ")
    fun getAllNoDone(): Flow<List<Task>>

    @Delete
    suspend fun delete(task: Task)

    @Query("SELECT * FROM TASK T WHERE T.taskId ==:id")
    suspend fun findById(id:Int):Task?

    @Transaction
    @Query("SELECT * FROM TodoCategory")
    fun getCategoriesWithTasks(): Flow<List<CategoryWithTasks>>

    @Update
    suspend fun updateTask(task:Task)


}