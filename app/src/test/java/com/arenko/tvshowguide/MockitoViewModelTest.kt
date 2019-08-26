package com.arenko.tvshowguide

import com.arenko.tvshowguide.model.Movie
import com.arenko.tvshowguide.network.MovieRepository
import com.arenko.tvshowguide.ui.movie.MovieListViewModel
import io.reactivex.Single
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations


class MockitoViewModelTest {

    @Mock
    private lateinit var movieRepository: MovieRepository

    private lateinit var viewModelMovie: MovieListViewModel

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        viewModelMovie = MovieListViewModel(movieRepository)
    }

    @Test
    fun showDataFromApi() {
        Mockito.`when`(movieRepository.getPopularTvShows(1))
            .thenReturn(Single.just(Movie()))

        viewModelMovie.initializePaging()
        if (viewModelMovie.movieList.value != null) {
            Assert.assertEquals(viewModelMovie.movieList.value!!.size, 20)
        }
    }
}