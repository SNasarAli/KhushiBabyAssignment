package com.khushibaby.assignment.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.io.Serializable

@Entity(tableName = "movie_list")
data class MovieDataClass(

    @SerializedName("adult")

    var adult: Boolean? = null,

    @SerializedName("backdrop_path")

    var backdropPath: String? = null,

    @PrimaryKey
    @SerializedName("id")

    var id: Int? = null,

    @SerializedName("original_language")

    var originalLanguage: String? = null,

    @SerializedName("original_title")

    var originalTitle: String? = null,

    @SerializedName("overview")

    var overview: String? = null,

    @SerializedName("popularity")

    var popularity: Double? = null,

    @SerializedName("poster_path")

    var posterPath: String? = null,

    @SerializedName("release_date")

    var releaseDate: String? = null,

    @SerializedName("title")

    var title: String? = null,

    @SerializedName("video")

    var video: Boolean? = null,

    @SerializedName("vote_average")

    var voteAverage: Double? = null,

    @SerializedName("vote_count")

    var voteCount: Int? = null
): Serializable