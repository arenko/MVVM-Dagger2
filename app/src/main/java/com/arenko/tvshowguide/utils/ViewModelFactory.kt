package com.arenko.tvshowguide.utils

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.arenko.tvshowguide.network.MovieRepository
import com.arenko.tvshowguide.ui.movie.MovieListViewModel
import javax.inject.Inject

class ViewModelFactory @Inject
constructor(private val repository: MovieRepository) :
    ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MovieListViewModel::class.java)) {
            return MovieListViewModel(repository) as T
        }

        throw IllegalArgumentException("Unknown´model")
    }

}