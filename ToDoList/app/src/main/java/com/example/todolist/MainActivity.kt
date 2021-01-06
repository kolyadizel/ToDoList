package com.example.todolist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var todoAdapter: TodoAdapter  //declare global adapter without initializing it
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        todoAdapter = TodoAdapter(mutableListOf()) //initialize

        rvTodoItems.adapter = todoAdapter
        //via LinearLayoutManager we define how items are arranged in our list
        rvTodoItems.layoutManager  = LinearLayoutManager(this)

        btnAddTodo.setOnClickListener{    //adds new item int out list after click
            val todoTitle = etTodoTitle.text.toString()
            if(todoTitle.isNotEmpty()){  //don't need empty todos
                val todo = ToDo(todoTitle)
                todoAdapter.addTodo(todo)
                etTodoTitle.text.clear()
            }
        }

        btnDeleteDoneTodos.setOnClickListener{
            todoAdapter.deleteDoneTodos()   //we already defined logic in adapter
        }
    }
}