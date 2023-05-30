package com.ryan.anmp_160420023_week8.model

import androidx.room.*

@Dao
interface TodoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg todo:Todo)

    @Query("Select * FROM todo")
    fun selectAllTodo():List<Todo>

    @Query("Select * FROM todo WHERE uuid = :id")
    fun selectTodo(id:Int):Todo

    @Delete
    fun deleteTodo(todo:Todo)
}