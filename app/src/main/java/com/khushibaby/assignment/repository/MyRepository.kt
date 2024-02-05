package com.khushibaby.assignment.repository

import com.khushibaby.assignment.localdb.MyDao
import com.khushibaby.assignment.model.MovieDataClass

class MyRepository(private val myDao: MyDao) {
    suspend fun insertItems(items: List<MovieDataClass>) {
        myDao.insertItems(items)
    }

    fun getItems(): List<MovieDataClass> {
        return myDao.getItems()
    }
}
