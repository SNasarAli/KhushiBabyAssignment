package com.khushibaby.assignment.model

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName

class MovieProductionCompany {

    @SerializedName("id")
    @Expose
    private var id: Int? = null

    @SerializedName("logo_path")
    @Expose
    private var logoPath: Any? = null

    @SerializedName("name")
    @Expose
    private var name: String? = null

}