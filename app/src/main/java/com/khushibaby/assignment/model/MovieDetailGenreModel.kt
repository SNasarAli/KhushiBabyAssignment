package com.khushibaby.assignment.model

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName

class MovieDetailGenreModel {

    @SerializedName("id")
    @Expose
     var id: Int? = null

    @SerializedName("name")
    @Expose
     var name: String? = null

}