package com.arenko.tvshowguide.network

import com.arenko.tvshowguide.data.ResultResponse

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApiInterface {

    @GET("tv/popular")
    fun getPopularTvShows(
        @Query("api_key") api_key: String,
        @Query("language") language: String,
        @Query("page") page: Int
    ): Single<ResultResponse>

}