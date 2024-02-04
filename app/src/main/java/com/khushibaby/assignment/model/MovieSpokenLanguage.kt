package com.khushibaby.assignment.model

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName

class MovieSpokenLanguage {

    @SerializedName("english_name")
    @Expose
    private var englishName: String? = null

    @SerializedName("iso_639_1")
    @Expose
    private var iso6391: String? = null

    @SerializedName("name")
    @Expose
    private var name: String? = null

}