package com.example.todoapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    /**
     * This function gets called whne the Add Button is clicked. It adds a todo into the database.
     * @param view MainActivity View
     */
    fun addTodo(view: View?){

    }

    /**
     * This function gets called whne the delete Button is clicked. It deletes a selected todo into the database.
     * @param view MainActivity View
     */
    fun deleteTodo(view: View?){

    }
}