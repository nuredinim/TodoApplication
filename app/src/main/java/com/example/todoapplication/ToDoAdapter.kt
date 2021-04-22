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

    /**
     * Initializes each of the items in the recyclerView and maps the
     * data in the mutableList of ToDos to it
     */
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

            // tell Kotlin that CheckBox isnt null
            // set.OnCheckedChangeListener to it
            todoCheckBox!!.setOnCheckedChangeListener { _, _ ->
                // reveres the value in the isChecked field of the current ToDo
                currentToDo.isChecked = !currentToDo.isChecked
            }
        }
    }

    /**
     * Returns the number of items in the MutableList of ToDos
     */
    override fun getItemCount(): Int {
         return todos.size
    }

    /**
     * This method gets called by the addTodo method in the MainActivity
     * when the add button is clicked. It will call the DBHandler method
     * that adds a ToDo into the database.
     */
    fun addTodo(dbHandler: DBHandler, name: String){

        // ask kotlin to check if the dbHandler is null
        // if it isnt, call its addToDo method passing the
        // specified ToDo name
        dbHandler?.addTodo(name)
        notifyDataSetChanged()
    }

    /**
     * This method gets called when the delete button is clicked in the MainActiviyt
     * @param dbHandler reference to DBHandler
     */
    fun deleteTodos(dbHandler: DBHandler){
        // iterate through mutable list of todos
        todos.forEach {
            // check if the current Todo's is checked is equal to true
            if (it.isChecked){
                // ask Kotlin to check if the dbHandler isnt null
                // if it isnt null, call its delete ToDO method
                // pass the current TODOs database id
                dbHandler?.deleteToDo(it.id)
            }
        }
        notifyDataSetChanged()
    }
}