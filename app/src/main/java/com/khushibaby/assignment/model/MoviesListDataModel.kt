package com.khushibaby.assignment.model

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName

class MoviesListDataModel(
    adult: Boolean,
    backdropPath: String,
    genreIds: List<Int?>?,
    id: Int,
    originalLanguage: String,
    originalTitle: String,
    overview: String,
    popularity: Double,
    posterPath: String,
    releaseDate: String,
    title: String,
    video: Boolean,
    voteAverage: Double,
    voteCount: Int
) {

    @SerializedName("adult")
    @Expose
     var adult: Boolean? = adult

    @SerializedName("backdrop_path")
    @Expose
     var backdropPath: String? = backdropPath

    @SerializedName("genre_ids")
    @Expose
     var genreIds: List<Int?>? = genreIds

    @SerializedName("id")
    @Expose
     var id: Int? = id

    @SerializedName("original_language")
    @Expose
     var originalLanguage: String? = originalLanguage

    @SerializedName("original_title")
    @Expose
     var originalTitle: String? = originalTitle

    @SerializedName("overview")
    @Expose
     var overview: String? = overview

    @SerializedName("popularity")
    @Expose
     var popularity: Double? = popularity

    @SerializedName("poster_path")
    @Expose
     var posterPath: String? = posterPath

    @SerializedName("release_date")
    @Expose
     var releaseDate: String? = releaseDate

    @SerializedName("title")
    @Expose
     var title: String? = title

    @SerializedName("video")
    @Expose
     var video: Boolean? = video

    @SerializedName("vote_average")
    @Expose
     var voteAverage: Double? = voteAverage

    @SerializedName("vote_count")
    @Expose
     var voteCount: Int? = voteCount
}