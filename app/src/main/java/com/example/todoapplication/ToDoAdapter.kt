package com.example.todoapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.appcompat.view.menu.ActionMenuItemView
import androidx.recyclerview.widget.RecyclerView

class ToDoAdapter(
        // declare a mutablelist of ToDos
        var todos: MutableList<ToDo>
) : RecyclerView.Adapter<ToDoAdapter.ToDoViewHolder>(){
    // declare a ViewHolder that will hold the layout of an item in
    // the RecyclerView
    class ToDoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    // declare TextView mutable field using null safety
    var todoTextView: TextView? = null

    // declare Checkbox mutable field using null safety
    var todoCheckBox: CheckBox? = null

    /**
     * Create a ToDoViewHolder that references the li_main layout resource
     * and return it.
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ToDoViewHolder {
        return ToDoViewHolder(LayoutInflater.from(parent.context).inflate(
                R.layout.li_main,
                parent,
                false
        ))
    }

    override fun onBindViewHolder(holder: ToDoViewHolder, position: Int) {
        // get current item in MutableList of ToDos and sotre it in
        // immutable variables
        val currentToDo = todos[position]

        holder.itemView.apply {
            // make TextView refer to TextView in li_main layout resource
            todoTextView = findViewById<View>(R.id.todoTextView) as TextView
            // tell Kotlin that TextView isnt null
            // assign the name value in the current item to text attribute of
            // Textview
            todoTextView!!.text = currentToDo.name

            // make CheckBox refer to the CheckBox in li_main layout resource
            todoCheckBox = findViewById<View>(R.id.todoCheckBox) as CheckBox

            // tell Kotlin that CheckBox isnt null
            // assign the name value in the current item to isChecked attribute of
            // Checkbox
            todoCheckBox!!.isChecked = currentToDo.isChecked
        }
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }


}