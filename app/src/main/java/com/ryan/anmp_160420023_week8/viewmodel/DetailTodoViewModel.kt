package com.ryan.anmp_160420023_week8.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.room.Room
import com.ryan.anmp_160420023_week8.model.Todo
import com.ryan.anmp_160420023_week8.model.TodoDatabase
import com.ryan.anmp_160420023_week8.util.buildDb
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class DetailTodoViewModel(application: Application): AndroidViewModel(application), CoroutineScope {
    private val job = Job()
    val todoLD = MutableLiveData<Todo>()

    fun addTodo(list:List<Todo>){
        launch {
            val db = buildDb(getApplication())

            db.todoDao().insertAll(*list.toTypedArray())
        }
    }

    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.IO

    fun fetch(id:Int){
        launch {
            val db = buildDb(getApplication())
            todoLD.postValue(db.todoDao().selectTodo(id))
        }
    }

    fun update(title:String, notes:String, priority:Int, id:Int){
        launch {
            val db = buildDb(getApplication())
            db.todoDao().update(title, notes, priority, id)
        }
    }
}