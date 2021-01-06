package com.example.todolist

//Create data class to hold abstract information about 2 values

data class ToDo (
    val title: String, //Tusk
    var isChecked: Boolean = false //Checked or not
)