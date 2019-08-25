package com.arenko.tvshowguide.data

import com.google.gson.annotations.Expose

class Movie {
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