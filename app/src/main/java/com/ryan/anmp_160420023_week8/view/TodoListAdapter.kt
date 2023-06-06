package com.ryan.anmp_160420023_week8.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageButton
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.ryan.anmp_160420023_week8.R
import com.ryan.anmp_160420023_week8.model.Todo

class TodoListAdapter(val todoList: ArrayList<Todo>, val adapterOnClick:(Todo)->Unit):RecyclerView.Adapter<TodoListAdapter.TodoViewHolder>() {
    class TodoViewHolder(var view: View): RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.todo_item_layout, parent, false)

        return TodoViewHolder(view)
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        var checktask = holder.view.findViewById<CheckBox>(R.id.checkTask)
        checktask.text = todoList[position].title
        checktask.setOnCheckedChangeListener{compoundButton, isChecked ->
            if(isChecked){
                adapterOnClick(todoList[position])
            }
        }

        holder.view.findViewById<ImageButton>(R.id.imgEdit).setOnClickListener{
            val action = TodoListFragmentDirections.actionEditTodoFragment(todoList[position].uuid)
            Navigation.findNavController(it).navigate(action)
        }
    }

    override fun getItemCount(): Int {
        return todoList.size
    }

    fun updateTodoList(newTodoList: List<Todo>){
        todoList.clear()
        todoList.addAll(newTodoList)
        notifyDataSetChanged()
    }
}