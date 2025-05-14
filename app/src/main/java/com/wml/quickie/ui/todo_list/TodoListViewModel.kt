package com.wml.quickie.ui.todo_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wml.quickie.data.TodoRepository
import com.wml.quickie.util.Routes
import com.wml.quickie.util.UIEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TodoListViewModel @Inject constructor(
    private val todoRepository: TodoRepository,
) : ViewModel() {
    val todos = todoRepository.getTodos()
    private val _uiEvent = Channel<UIEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()


    fun onEvent(event: TodoListEvent) {
        when (event) {
            is TodoListEvent.onDeleteTodo -> {
            }

            is TodoListEvent.onToggleTodo -> {

            }

            is TodoListEvent.onAddTodoClick -> {
                sendUIEvent(UIEvent.Navigate(Routes.ADD_EDIT_TODO_SCREEN))
            }

            is TodoListEvent.onUndoDeleteClick -> {

            }

            is TodoListEvent.onTodoClick -> {
                sendUIEvent(UIEvent.Navigate(Routes.ADD_EDIT_TODO_SCREEN + "?${Routes.ADD_TODO}=${event.todo.id}"));
            }
        }
    }

    private fun sendUIEvent(event: UIEvent) {
        viewModelScope.launch(
            block = {
                _uiEvent.send(event)
            }
        );
    }
}