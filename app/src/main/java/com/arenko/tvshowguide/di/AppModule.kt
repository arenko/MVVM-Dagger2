package com.arenko.tvshowguide.di

import androidx.lifecycle.ViewModelProvider
import com.arenko.tvshowguide.network.MovieApiInterface
import com.arenko.tvshowguide.network.MovieRepository
import com.arenko.tvshowguide.utils.Constants
import com.arenko.tvshowguide.utils.ViewModelFactory
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class AppModule {
    @Singleton
    @Provides
    internal fun provideRetrofit(): Retrofit {
        return Retrofit.Builder().baseUrl(Constants.BASE_URL)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    internal fun provideApi(retrofit: Retrofit): MovieApiInterface {
        return retrofit.create(MovieApiInterface::class.java)
    }

    @Provides
    @Singleton
    fun getMovieRepository(movieApiInterface: MovieApiInterface): MovieRepository {
        return MovieRepository(movieApiInterface)
    }

    @Provides
    @Singleton
    fun getViewModelFactory(movieRepository: MovieRepository): ViewModelProvider.Factory {
        return ViewModelFactory(movieRepository)
    }
}
