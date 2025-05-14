package com.wml.quickie.ui.todo_list

import com.wml.quickie.data.Todo

sealed class TodoListEvent {
    data class onDeleteTodo(val todo: Todo) : TodoListEvent()
    data class onToggleTodo(val todo: Todo, val isChecked: Boolean) : TodoListEvent()
    object onUndoDeleteClick : TodoListEvent()
    data class onTodoClick(val todo: Todo) : TodoListEvent()
    object onAddTodoClick : TodoListEvent()
}