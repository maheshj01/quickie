package com.wml.quickie.data

import kotlinx.coroutines.flow.Flow

class TodoRepositoryImpl(
    private val todoDao: TodoDao,
    private val todoDatabase: TodoDatabase
) : TodoRepository {
    override suspend fun getTodos(): Flow<List<Todo>> {
        return todoDao.getTodos()
    }

    override suspend fun getById(id: Int): Todo? {
        return todoDao.getById(id)
    }

    override suspend fun insert(todo: Todo): Long {
        return todoDao.insert(todo)
    }

    override suspend fun update(todo: Todo): Int {
        return todoDao.update(todo)
    }

    override suspend fun delete(todo: Todo): Int {
        return todoDao.delete(todo)
    }

    override suspend fun deleteById(id: Int): Int {
        return todoDao.deleteById(id)
    }

}