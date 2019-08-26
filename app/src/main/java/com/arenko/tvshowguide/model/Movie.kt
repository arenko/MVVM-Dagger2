package com.arenko.tvshowguide.model

import com.google.gson.annotations.Expose

class Movie {
    @Expose
    var results: List<Movie>? = null
    @Expose
    var page: Int = 0
    @Expose
    var vote_average: Double = 0.toDouble()
    @Expose
    var id: Int = 0
    @Expose
    var name: String? = null
    @Expose
    var poster_path: String? = null
    @Expose
    var first_air_date: String? = null
}