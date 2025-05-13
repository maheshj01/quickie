package com.wml.quickie.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
@Dao
interface TodoDao {
    @Query("SELECT * FROM todo ORDER BY createdAt DESC")
    suspend fun getTodos(): Flow<List<Todo>>
    @Query("SELECT * FROM todo WHERE id = :id")
    suspend fun getById(id: Int): Todo?
    @Insert
    suspend fun insert(todo: Todo): Long
    suspend fun update(todo: Todo): Int
    @Delete
    suspend fun delete(todo: Todo): Int
    @Delete
    suspend fun deleteById(id: Int): Int
}