package com.arenko.tvshowguide.ui.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.arenko.tvshowguide.data.Movie
import com.arenko.tvshowguide.network.MovieRepository
import com.arenko.tvshowguide.ui.movie.datasource.MovieDataSourceFactory
import io.reactivex.disposables.CompositeDisposable

class MovieListViewModel(private val movieRepository: MovieRepository) : ViewModel() {
    private val disposable = CompositeDisposable()
    lateinit var movieList: LiveData<PagedList<Movie>>
    private lateinit var movieDataSourceFactory: MovieDataSourceFactory

    fun initializePaging() {
        movieDataSourceFactory = MovieDataSourceFactory(movieRepository, disposable)
        val config = PagedList.Config.Builder()
            .setPageSize(20)
            .setEnablePlaceholders(false)
            .build()
        movieList =
            LivePagedListBuilder<Int, Movie>(movieDataSourceFactory, config).build()
    }

    fun retry() {
        movieDataSourceFactory.sourceLiveData.value?.retry()
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }
}
