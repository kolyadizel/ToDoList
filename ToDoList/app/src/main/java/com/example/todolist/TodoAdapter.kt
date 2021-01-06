package com.example.todolist

import android.graphics.Paint.STRIKE_THRU_TEXT_FLAG
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_todo.view.*

//Our adapter or main logic

class TodoAdapter(
    private val  todos: MutableList<ToDo>
    //Build constructor with 1 private value
    //MutableList with type To Do(Our Data class)
) : RecyclerView.Adapter<TodoAdapter.TodoViewHolder>() {

    class TodoViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)
    //Inner class inherits RecyclerView.ViewHolder


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        //This function will create our ViewHolder
        //Everything we need to do is define how a specific item in our list looks like

        return TodoViewHolder(
            LayoutInflater.from(parent.context).inflate(
                //Use inflater to "convert" XML code to Kotlin code, so we can use our view

                R.layout.item_todo,  //layout we want inflate
                parent,  //view group
                false   //we don't need this for recycle view
            )
        )
    }

    fun addTodo(todo: ToDo){
        //adds new element to our list
        todos.add(todo)
        notifyItemInserted(todos.size-1)    //last position
    }

    fun deleteDoneTodos(){
        //delete element from the list
        todos.removeAll { todo ->
            todo.isChecked
        }
        notifyDataSetChanged()
    }

    private fun toggleStrikeThrough(tvTodoTitle : TextView, isChacked: Boolean){
        //this function strikethrough text if it's checked
        if(isChacked){
            tvTodoTitle.paintFlags = tvTodoTitle.paintFlags or STRIKE_THRU_TEXT_FLAG

        } else{
            //if isn't checked remove flag so text become normal
            tvTodoTitle.paintFlags = tvTodoTitle.paintFlags and STRIKE_THRU_TEXT_FLAG.inv()
        }
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        //this func will bind our data from tоdo list to the views of our list
        //it will be called when a new viewholder is visible so a new item in our list is visible

        val curTodo = todos[position]
        holder.itemView.apply {
            //use apply to execute block of code for this object

            tvTodoTitle.text = curTodo.title    //set new title if changed
            cbDone.isChecked = curTodo.isChecked    //set new status if changed
            toggleStrikeThrough(tvTodoTitle, curTodo.isChecked) // apply toggleStrikeThrough func
            cbDone.setOnCheckedChangeListener { _, isChecked ->
                toggleStrikeThrough(tvTodoTitle,isChecked) //use new isChecked status
                curTodo.isChecked = !curTodo.isChecked // true -> false, false -> true
            }
        }
    }

    override fun getItemCount(): Int {
        return todos.size   //this function returns amount of items in our list
    }





}