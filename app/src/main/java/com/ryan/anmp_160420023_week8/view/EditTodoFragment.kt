package com.ryan.anmp_160420023_week8.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.ryan.anmp_160420023_week8.R
import com.ryan.anmp_160420023_week8.viewmodel.DetailTodoViewModel
import com.ryan.anmp_160420023_week8.viewmodel.ListTodoViewModel

class EditTodoFragment : Fragment() {
    private lateinit var viewModel: DetailTodoViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_create_todo, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(DetailTodoViewModel::class.java)

        view.findViewById<TextView>(R.id.txtJudulTodo).text = "Edit Todo"
        view.findViewById<Button>(R.id.btnAdd).text = "Save Changes"

        val id = EditTodoFragmentArgs.fromBundle(requireArguments()).id
        viewModel.fetch(id)
        observeViewModel()

        view.findViewById<Button>(R.id.btnAdd).setOnClickListener {
            val radioGroup = view.findViewById<RadioGroup>(R.id.radioGroupPriority)
            val radio = view.findViewById<RadioButton>(radioGroup.checkedRadioButtonId)
            val txtTitle = view.findViewById<TextView>(R.id.txtTitle)
            val txtNotes = view.findViewById<TextView>(R.id.txtNotes)
            viewModel.update(txtTitle.text.toString(), txtNotes.text.toString(), radio.tag.toString().toInt(), id)
            Toast.makeText(view.context, "Todo Updated", Toast.LENGTH_SHORT).show()
            Navigation.findNavController(it).popBackStack()
        }
    }

    fun observeViewModel(){
        viewModel.todoLD.observe(viewLifecycleOwner, Observer{
            view?.findViewById<TextView>(R.id.txtTitle)?.setText(it.title)
            view?.findViewById<TextView>(R.id.txtNotes)?.setText(it.notes)
            when(it.priority){
                1 -> view?.findViewById<RadioButton>(R.id.radioLow)?.isChecked = true
                2 -> view?.findViewById<RadioButton>(R.id.radioMedium)?.isChecked = true
                else -> view?.findViewById<RadioButton>(R.id.radioHigh)?.isChecked = true
            }
        })
    }
}