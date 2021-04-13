package com.example.todoapplication

/**
 * This ToDo data class has three fields that map to the
 * columns in the todo table in the database. It will be used
 * to exchange data between the database and the RecyclerView/
 */
data class ToDo (
        // declare a mutable Int to store the todo id
        var id: Int,
        // declare an immutable String to store the todo name
        val name: String,
        // declare a mutable Boolean to store the todo is_checked
        var isChecked: Boolean = false
        )