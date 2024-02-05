package com.khushibaby.assignment.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.khushibaby.assignment.model.MovieDataClass
import com.khushibaby.assignment.repository.MyRepository
import kotlinx.coroutines.launch

class MyViewModel(private val repository: MyRepository) : ViewModel() {
    fun insertItems(items: List<MovieDataClass>) {
        viewModelScope.launch {
            repository.insertItems(items)
        }
    }

    fun getItems(): List<MovieDataClass> {
        return repository.getItems().asReversed()
    }

}
