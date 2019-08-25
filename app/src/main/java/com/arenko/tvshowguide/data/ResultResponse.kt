package com.arenko.tvshowguide.data

import com.google.gson.annotations.Expose

class ResultResponse {
    @Expose
    var results: List<Movie>? = null
    @Expose
    var page: Int = 0
}