package com.wml.quickie.ui.todo_list

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.SnackbarResult
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.wml.quickie.util.Routes
import com.wml.quickie.util.UIEvent

@Composable
fun TodoListScreen(
    onNavigate: (
        UIEvent.Navigate,
    ) -> Unit,
    viewmodel: TodoListViewModel = hiltViewModel()
) {
    val todos = viewmodel.todos.collectAsState(initial = emptyList())
    val scaffoldState = rememberScaffoldState()
    LaunchedEffect(key1 = true) {
        viewmodel.uiEvent.collect { event ->
            when (event) {
                is UIEvent.Navigate -> {
                    onNavigate(event)
                }

                is UIEvent.ShowSnackbar -> {
                    var result = scaffoldState.snackbarHostState.showSnackbar(
                        message = event.message,
                        actionLabel = event.action
                    )
                    if (result == SnackbarResult.ActionPerformed) {
                        viewmodel.onEvent(TodoListEvent.onUndoDeleteClick)
                    }
                }

                is UIEvent.popBackStack -> {
                    onNavigate(UIEvent.Navigate(Routes.TODO_LIST_SCREEN))
                }
            }
        }
    }

    Scaffold(
        scaffoldState = scaffoldState,
        floatingActionButton = {
            FloatingActionButton(onClick = {
                viewmodel.onEvent(TodoListEvent.onAddTodoClick)
            }) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Add Todo",
                    modifier = Modifier
                )
            }
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier.padding(innerPadding)
        ) {
            items(todos.value.size) { index ->
                val todo = todos.value[index]
                TodoListItem(
                    todo = todo,
                    onEvent = viewmodel::onEvent,
                    modifier = Modifier
                )
            }
        }
    }
}