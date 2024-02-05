package com.khushibaby.assignment.model

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName

class MoviesListModel {
    @SerializedName("page")
    @Expose
     var page: Int? = null

    @SerializedName("results")
    @Expose
     var results: List<MovieDataClass>? = null

    @SerializedName("total_pages")
    @Expose
     var totalPages: Int? = null

    @SerializedName("total_results")
    @Expose
     var totalResults: Int? = null
}