package com.wml.quickie.data

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

interface TodoRepository {
    fun getTodos(): Flow<List<Todo>>
    suspend fun getById(id: Int): Todo?
    suspend fun insert(todo: Todo): Long
    suspend fun update(todo: Todo): Int
    suspend fun delete(todo: Todo): Int
    suspend fun deleteById(id: Int): Int
}