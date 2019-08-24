package com.arenko.tvshowguide.network

import com.arenko.tvshowguide.data.Movie
import com.arenko.tvshowguide.data.ResultResponse
import com.arenko.tvshowguide.utils.Constants
import io.reactivex.Observable
import io.reactivex.Single
import javax.inject.Inject


class MovieRepository @Inject
constructor(private val movieApiInterface: MovieApiInterface) {

    fun getPopularTvShows(page: Int): Single<ResultResponse> {
        return movieApiInterface.getPopularTvShows(Constants.API_KEY, Constants.language, page)
    }
}