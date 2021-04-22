package com.example.todoapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    // declare EditText as mutable field using null safety
    var todoEditText: EditText? = null

    // declare RecyclerView as mutable field using null safety
    var todoRecyclerView: RecyclerView? = null

    // declare DBHandler as mutable field using null safety
    var dbHandler: DBHandler? = null

    // declare a ToDoAdapter as a mutable field
    // specify that it will be initialized later
    lateinit var toDoAdapter: ToDoAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // make EditText refer to actual EditText in activity_main layout resource
        todoEditText = findViewById<View>(R.id.todoEditText) as EditText

        // make RecyclerView refer to actual RecyclerView in activity_main layout resource
        todoRecyclerView = findViewById<View>(R.id.todoRecyclerView) as RecyclerView

        // full initilize dbHandler
        dbHandler = DBHandler(this, null)

        // initialize the toDoAdapter
        toDoAdapter = ToDoAdapter(dbHandler!!.todos)

        // tell Kotlin that RecyclerView isnt null and set the toDoAdapter on it
        todoRecyclerView!!.adapter = toDoAdapter

        // tell Kotlin that the Recycler view isnt null and apply a Linear Layout to it
        todoRecyclerView!!.layoutManager = LinearLayoutManager(this)
    }

    /**
     * This function gets called whne the Add Button is clicked. It adds a todo into the database.
     * @param view MainActivity View
     */
    fun addTodo(view: View?){

        // tell Kotlin that EditText isnt null
        // get text input in EditText as string
        // store it in variable
        val todoName = todoEditText!!.text.toString()

        // trim variable and check if its equal to an empty string
        if(todoName.trim() == ""){
            // display "Please enter a Todo! Toast
            Toast.makeText(this, "Please enter a Todo!", Toast.LENGTH_LONG).show()
        } else {
            // ask Kotlin to check if the dbHandler is null
            // if its not, apply all of the code in the let block to it
            // in the let block, may refer to the dbHandler as it
            dbHandler?.let {
                // call method in toDoAdapter that will add ToDo into database
                toDoAdapter.addTodo(it, todoName)
            }

            // display "Todo added!" toast
            Toast.makeText(this, "Todo added!", Toast.LENGTH_LONG).show()

            // clear editText
            todoEditText!!.text.clear()

            // call notifyAdapter method
            notifyAdapter()
        }
    }

    /**
     * This function gets called whne the delete Button is clicked. It deletes a selected todo into the database.
     * @param view MainActivity View
     */
    fun deleteTodo(view: View?){

        // ask Kotlin to check if the dbHandler is null
        // if it isnt null, pass it to the deleteToDos method
        // in the toDoAdapter
        dbHandler?.let {
            toDoAdapter.deleteTodos(it)
        }

        // call notifyAdapter method
        notifyAdapter()
    }

    /**
     * This method updates the MutableListas of ToDos in the toDoAdapter
     * with the most current data in the database
     */
    private fun notifyAdapter(){
        // tell Kotlin that dbHandler isnt null
        // tell its getter method that returns the data in the
        // database as a Mutable List
        // assign Mutable list to toDoAdapters MutableList of ToDos
        toDoAdapter.todos = dbHandler!!.todos
    }
}