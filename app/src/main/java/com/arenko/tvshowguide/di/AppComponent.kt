package com.arenko.tvshowguide.di

import com.arenko.tvshowguide.ui.movie.MovieListFragment
import dagger.Component
import javax.inject.Singleton

@Component(modules = arrayOf(AppModule::class, ContextModule::class))
@Singleton
interface AppComponent {

    fun doInjection(movieListFragment: MovieListFragment)

}