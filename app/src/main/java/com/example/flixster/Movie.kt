package com.example.flixster

import com.google.gson.annotations.SerializedName

class Movie {
    @JvmField
    @SerializedName("title")
    var title: String? = null

    @SerializedName("poster_path")
    var poster_path: String? = null

    @JvmField
    @SerializedName("overview")
    var overview: String? = null

    //TODO-STRETCH-GOALS amazonUrl
}