package com.arenko.tvshowguide.ui.movie.datasource

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.arenko.tvshowguide.model.Movie
import com.arenko.tvshowguide.network.MovieRepository
import io.reactivex.disposables.CompositeDisposable

class MovieDataSourceFactory(
    private val repository: MovieRepository,
    private val compositeDisposable: CompositeDisposable
) : DataSource.Factory<Int, Movie>() {

    val sourceLiveData = MutableLiveData<MovieDataSource>()

    override fun create(): DataSource<Int, Movie> {
        val source = MovieDataSource(repository, compositeDisposable)
        sourceLiveData.postValue(source)
        return source
    }
}