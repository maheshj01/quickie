package com.wml.quickie.ui.todo_list

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.material3.Checkbox
import androidx.compose.material3.SwipeToDismissBox
import androidx.compose.material3.SwipeToDismissBoxValue
import androidx.compose.material3.Text
import androidx.compose.material3.rememberSwipeToDismissBoxState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.wml.quickie.data.Todo

@Composable
fun TodoListItem(
    todo: Todo,
    onEvent: (TodoListEvent) -> Unit,
    modifier: Modifier
) {
    //    with a checkbox, title, description
    //    create a beautiful todo list item
    //    delete is triggered by swipe

    //    toggle is triggered by checkbox
    var dismissState = rememberSwipeToDismissBoxState(
        confirmValueChange = {
            if (it == SwipeToDismissBoxValue.EndToStart) {
                onEvent(TodoListEvent.onDeleteTodo(todo))
            }
            false
        }
    )
    SwipeToDismissBox(
        modifier = modifier,
        state = dismissState,
        backgroundContent = {
            Row(modifier) {
                // add delete icon here
                // add delete text here
            }
        },
        content = {
            Row(
                modifier = modifier,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(modifier, verticalArrangement = Arrangement.Center) {
                    Text(
                        text = todo.title,
                        modifier = modifier
                    )
                    Text(
                        text = todo.description ?: "",
                        modifier = modifier
                    )
                }
                Spacer(modifier)
                // checkbox here
                Checkbox(
                    checked = todo.isCompleted,
                    onCheckedChange = {
                        onEvent(TodoListEvent.onToggleTodo(todo, it))
                    },
                    modifier = modifier
                )
            }
        }
    )


}