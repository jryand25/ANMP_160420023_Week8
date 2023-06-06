package com.ryan.anmp_160420023_week8.model

import androidx.room.*

@Dao
interface TodoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg todo:Todo)

    @Query("Select * FROM todo WHERE is_done = 0 ORDER BY priority DESC")
    suspend fun selectAllTodo():List<Todo>

    @Query("Select * FROM todo WHERE uuid = :id")
    fun selectTodo(id:Int):Todo

    @Query("UPDATE todo SET title = :title, notes = :notes, priority = :priority WHERE uuid = :id")
    fun update(title:String, notes:String, priority:Int, id:Int)

    @Query("UPDATE todo SET is_done = 1 WHERE uuid = :id")
    fun checkTodo(id:Int)

    @Delete
    fun deleteTodo(todo:Todo)
}