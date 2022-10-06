package com.example.flixster

import com.google.gson.annotations.SerializedName

class Movie {
    @JvmField
    @SerializedName("title")
    var title: String? = null

    @JvmField
    @SerializedName("poster_path")
    var poster_path: String? = null

    @JvmField
    @SerializedName("backdrop_path")
    var backdrop_path: String? = null

    @JvmField
    @SerializedName("overview")
    var overview: String? = null

    @JvmField
    @SerializedName("vote_average")
    var vote_average: Double? = null

    @JvmField
    @SerializedName("release_date")
    var releaseDate: String? = null
}