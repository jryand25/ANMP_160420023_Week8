package com.ryan.anmp_160420023_week8.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.ryan.anmp_160420023_week8.R
import com.ryan.anmp_160420023_week8.viewmodel.ListTodoViewModel

class TodoListFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_todo_list, container, false)
    }

    private lateinit var viewModel:ListTodoViewModel
    private val todoListAdapter = TodoListAdapter(arrayListOf(), {item->viewModel.clearTask(item)})

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(ListTodoViewModel::class.java)
        viewModel.refresh()
        var recViewTodo = view.findViewById<RecyclerView>(R.id.recViewTodo)
        recViewTodo.layoutManager = LinearLayoutManager(context)
        recViewTodo.adapter = todoListAdapter

        view.findViewById<FloatingActionButton>(R.id.fabAddTodo).setOnClickListener{
            val action = TodoListFragmentDirections.actionCreateTodo()
            Navigation.findNavController(it).navigate(action)
        }

        observeViewModel()
    }

    fun observeViewModel(){
        viewModel.todoLD.observe(viewLifecycleOwner, Observer{
            todoListAdapter.updateTodoList(it)
            var txtEmpty = view?.findViewById<TextView>(R.id.txtEmpty)
            if(it.isEmpty()){
                txtEmpty?.visibility = View.VISIBLE
            }
            else{
                txtEmpty?.visibility = View.GONE
            }
        })
    }
}