package com.khushibaby.assignment.model

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName




class MovieDetailModel {
    @SerializedName("adult")
    @Expose
    private var adult: Boolean? = null

    @SerializedName("backdrop_path")
    @Expose
    private var backdropPath: String? = null

    @SerializedName("belongs_to_collection")
    @Expose
    private var belongsToCollection: MovieDetailCollectionData? = null

    @SerializedName("budget")
    @Expose
    private var budget: Int? = null

    @SerializedName("genres")
    @Expose
    private var genres: List<MovieDetailGenreModel?>? = null

    @SerializedName("homepage")
    @Expose
    private var homepage: String? = null

    @SerializedName("id")
    @Expose
    private var id: Int? = null

    @SerializedName("imdb_id")
    @Expose
    private var imdbId: String? = null

    @SerializedName("original_language")
    @Expose
    private var originalLanguage: String? = null

    @SerializedName("original_title")
    @Expose
    private var originalTitle: String? = null

    @SerializedName("overview")
    @Expose
    private var overview: String? = null

    @SerializedName("popularity")
    @Expose
    private var popularity: Double? = null

    @SerializedName("poster_path")
    @Expose
    private var posterPath: String? = null

    @SerializedName("production_companies")
    @Expose
    private var productionCompanies: List<MovieProductionCompany?>? = null

    @SerializedName("production_countries")
    @Expose
    private var productionCountries: List<MovieProductionCountry?>? = null

    @SerializedName("release_date")
    @Expose
    private var releaseDate: String? = null

    @SerializedName("revenue")
    @Expose
    private var revenue: Int? = null

    @SerializedName("runtime")
    @Expose
    private var runtime: Int? = null

    @SerializedName("spoken_languages")
    @Expose
    private var spokenLanguages: List<MovieSpokenLanguage?>? = null

    @SerializedName("status")
    @Expose
    private var status: String? = null

    @SerializedName("tagline")
    @Expose
    private var tagline: String? = null

    @SerializedName("title")
    @Expose
    private var title: String? = null

    @SerializedName("video")
    @Expose
    private var video: Boolean? = null

    @SerializedName("vote_average")
    @Expose
    private var voteAverage: Double? = null

    @SerializedName("vote_count")
    @Expose
    private var voteCount: Int? = null

}