package com.ryan.anmp_160420023_week8.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.ryan.anmp_160420023_week8.R
import com.ryan.anmp_160420023_week8.model.Todo
import com.ryan.anmp_160420023_week8.viewmodel.DetailTodoViewModel

class CreateTodoFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_create_todo, container, false)
    }

    private lateinit var viewModel: DetailTodoViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(DetailTodoViewModel::class.java)

        view.findViewById<Button>(R.id.btnAdd).setOnClickListener {
            val txtTitle = view.findViewById<EditText>(R.id.txtTitle)
            val txtNotes = view.findViewById<EditText>(R.id.txtNotes)
            val radioGroup = view.findViewById<RadioGroup>(R.id.radioGroupPriority)
            val radio = view.findViewById<RadioButton>(radioGroup.checkedRadioButtonId)
            var todo = Todo(txtTitle.text.toString(), txtNotes.text.toString(), radio.tag.toString().toInt())
            val list = listOf(todo)
            viewModel.addTodo(list)
            Toast.makeText(view.context, "Data Added", Toast.LENGTH_SHORT).show()
            Navigation.findNavController(it).popBackStack()
        }
    }
}