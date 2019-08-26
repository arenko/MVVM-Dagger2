package com.arenko.tvshowguide.ui.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.arenko.tvshowguide.model.Movie
import com.arenko.tvshowguide.network.MovieRepository
import com.arenko.tvshowguide.ui.movie.datasource.MovieDataSourceFactory
import io.reactivex.disposables.CompositeDisposable

class MovieListViewModel(private val movieRepository: MovieRepository) : ViewModel() {
    private val disposable = CompositeDisposable()
    lateinit var movieList: LiveData<PagedList<Movie>>
    private lateinit var movieDataSourceFactory: MovieDataSourceFactory

    companion object {
        const val PAGE_SIZE = 20
    }

    fun initializePaging() {
        movieDataSourceFactory = MovieDataSourceFactory(movieRepository, disposable)
        val config = PagedList.Config.Builder()
            .setPageSize(PAGE_SIZE)
            .setEnablePlaceholders(false)
            .build()
        movieList =
            LivePagedListBuilder<Int, Movie>(movieDataSourceFactory, config).build()
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }
}
