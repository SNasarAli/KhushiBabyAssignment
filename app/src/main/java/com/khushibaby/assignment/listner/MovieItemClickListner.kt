package com.khushibaby.assignment.listner

import com.khushibaby.assignment.model.MovieDataClass

interface MovieItemClickListner {
    fun onItemClick(dataObject: MovieDataClass)
}