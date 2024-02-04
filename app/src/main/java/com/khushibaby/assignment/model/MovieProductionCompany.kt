package com.khushibaby.assignment.model

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName

class MovieProductionCompany {

    @SerializedName("id")
    @Expose
     var id: Int? = null

    @SerializedName("logo_path")
    @Expose
     var logoPath: Any? = null

    @SerializedName("name")
    @Expose
     var name: String? = null

}