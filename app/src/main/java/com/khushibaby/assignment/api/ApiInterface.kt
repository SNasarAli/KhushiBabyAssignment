package com.khushibaby.assignment.api

import com.khushibaby.assignment.model.MovieDetailModel
import com.khushibaby.assignment.model.MoviesListModel
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiInterface {
    @GET("popular")
    fun getPopularMovies(
        @Query("language") language: String,
        @Query("page") page: Int,
        @Query("api_key") api_key: String,
    ): Call<MoviesListModel>

    @GET("{movie_id}")
    fun getMovieDetail(
        @Path("movie_id") movie_id: String,
        @Query("language") language: String,
        @Query("api_key") api_key: String,
    ): Call<MovieDetailModel>
}